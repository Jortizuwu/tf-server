package com.typefigth.quote.application.services.quote;

import com.typefigth.quote.application.dtos.quote.ExternalQuoteDto;
import com.typefigth.quote.infrastructure.utils.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExternalService {

    private final WebClient webClient;

    ExternalService(WebClient webClient) {
        this.webClient = webClient;
    }

    // TODO: add limit=5
    public Mono<ExternalQuoteDto[]> getRandomQuote() {
        return webClient.get()
                .uri(Constants.EXTERNAL_QUOTE_URL + "/random?minLength=100&maxLength=100")
                .retrieve()
                .bodyToMono(ExternalQuoteDto[].class);
    }

    public Mono<ExternalQuoteDto> getQuote(String id) {
        return webClient.get()
                .uri(Constants.EXTERNAL_QUOTE_URL + "/quote/{id}", id)
                .retrieve()
                .bodyToMono(ExternalQuoteDto.class);
    }

    public Mono<?> getMatchByID(String matchID) {
        return webClient.get()
                .uri(Constants.MATCH_URL + "/{id}", matchID)
                .retrieve()
                .bodyToMono(ExternalQuoteDto.class);
    }
}