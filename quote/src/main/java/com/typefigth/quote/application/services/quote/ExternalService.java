package com.typefigth.quote.application.services.quote;

import com.typefigth.quote.domain.models.quote.Quote;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExternalService {

    private final WebClient webClient;

    ExternalService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Quote[]> getRandomQuote() {
        return webClient.get()
                .uri("/random?minLength=100&maxLength=100")
                .retrieve()
                .bodyToMono(Quote[].class);
    }

    public Mono<Quote> getQuote(Quote id) {
        return webClient.get()
                .uri("/quote/{id}", id)
                .retrieve()
                .bodyToMono(Quote.class);
    }

}