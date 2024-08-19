package com.typefigth.quote.infrastructure.config;

import com.typefigth.quote.application.services.quote.QuoteService;
import com.typefigth.quote.application.usecases.quote.CreateQuoteUseCaseImpl;
import com.typefigth.quote.application.usecases.quote.GetQuoteUseCaseImpl;
import com.typefigth.quote.application.usecases.quote.ListQuotesUseCaseImpl;
import com.typefigth.quote.domain.ports.out.quote.QuoteRepositoryPort;
import com.typefigth.quote.infrastructure.adapters.quote.JpaQuoteRepositoryAdapter;
import com.typefigth.quote.infrastructure.adapters.quote.mapper.QuoteMapper;
import com.typefigth.quote.infrastructure.adapters.quote.mapper.QuoteMapperAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {

    @Bean
    public QuoteService quoteService(QuoteRepositoryPort quoteRepositoryPort) {
        return new QuoteService(new CreateQuoteUseCaseImpl(quoteRepositoryPort), new GetQuoteUseCaseImpl(quoteRepositoryPort), new ListQuotesUseCaseImpl(quoteRepositoryPort));
    }

    @Bean
    public QuoteRepositoryPort quoteRepositoryPort(JpaQuoteRepositoryAdapter jpaQuoteRepositoryAdapter) {
        return jpaQuoteRepositoryAdapter;
    }

    @Bean
    public QuoteMapper quoteMapper() {
        return new QuoteMapperAdapter();
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.quotable.io/quotes")
                .build();
    }

}