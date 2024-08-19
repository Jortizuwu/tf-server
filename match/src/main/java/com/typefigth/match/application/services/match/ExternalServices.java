package com.typefigth.match.application.services.match;

import com.typefigth.match.domain.models.Quote;
import com.typefigth.match.domain.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExternalServices {

    private final Logger logger = LoggerFactory.getLogger(ExternalServices.class);
    private final WebClient webClient;

    public ExternalServices(WebClient webClient) {
        this.webClient = webClient;
    }

    public User findUserById(String id) {
        return webClient.get().uri("http://localhost:8080/user/" + id).retrieve().bodyToMono(User.class).onErrorResume(this::applyUser).block();
    }

    public List listQuoteByMatchId(String id) {
        return webClient.get().uri("http://localhost:8084/quote/match/" + id).retrieve().bodyToMono(List.class).onErrorResume(this::applyQuote).block();
    }

    public Mono<Void> createQuote(String id) {
        Map<String, String> body = Map.of("matchId", id);

        return webClient.post()
                .uri("http://localhost:8084/quote")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(throwable -> {
                    logger.error("Error al crear la cita: " + throwable.getMessage());
                    return Mono.error(new RuntimeException("Error al crear la cita", throwable));
                });
    }

    private Mono<? extends User> applyUser(Throwable e) {
        logger.error("Error fetching user data: {}", e.getMessage());
        return Mono.just(new User());
    }

    private Mono<? extends List<Quote>> applyQuote(Throwable e) {
        logger.error("Error fetching quote data: {}", e.getMessage());
        return Mono.just(new ArrayList<>());
    }

}
