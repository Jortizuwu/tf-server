package com.typefigth.api_gateway.infrastructure.adapters.filters;

import com.typefigth.api_gateway.application.dtos.TokenDto;
import com.typefigth.api_gateway.infrastructure.exceptions.UnAuthenticationException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final WebClient.Builder webClient;

    public AuthFilter(WebClient.Builder webClient) {
        super(Config.class);
        this.webClient = webClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        try {
            return ((((exchange, chain) -> {

                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                    return onError(exchange, HttpStatus.UNAUTHORIZED);

                String tokenHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                String[] chunks = tokenHeader.split(" ");

                if (chunks.length != 2 || !chunks[0].equals("Bearer"))
                    return onError(exchange, HttpStatus.UNAUTHORIZED);

                return webClient.build().post()
                        .uri("http://localhost:8085/auth/validate")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + chunks[1])
                        .retrieve()
                        .bodyToMono(TokenDto.class)
                        .then(Mono.defer(() -> chain.filter(exchange)))
                        .onErrorResume(WebClientResponseException.Unauthorized.class, e -> {
                            return onError(exchange, HttpStatus.UNAUTHORIZED);
                        })
                        .onErrorResume(Exception.class, e -> {
                            throw new UnAuthenticationException(e.getMessage());
                        });
            })));
        } catch (Exception e) {
            throw new UnAuthenticationException(e.getMessage());
        }
    }

    public Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    public static class Config {

    }

}
