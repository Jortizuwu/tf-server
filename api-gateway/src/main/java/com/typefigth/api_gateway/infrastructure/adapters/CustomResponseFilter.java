package com.typefigth.api_gateway.infrastructure.adapters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CustomResponseFilter extends AbstractGatewayFilterFactory<CustomResponseFilter.Config> {

    public CustomResponseFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpResponse response = exchange.getResponse();
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (response.getStatusCode() == HttpStatus.OK) {
                    response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json");
                    WebClient.create()
                            .get()
                            .uri(exchange.getRequest().getURI())
                            .retrieve()
                            .bodyToMono(String.class)
                            .map(body -> {
                                String newBody = "{ \"data\": " + body + ", \"hola\": \"hola\" }";
                                return Mono.just(response.bufferFactory().wrap(newBody.getBytes())).toString();
                            })
                            .subscribe();
                }
            }));
        };
    }

    public static class Config {
    }

}