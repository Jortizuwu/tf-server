package com.typefigth.match.infrastructure.config;

import com.typefigth.match.application.services.match.ExternalServices;
import com.typefigth.match.application.services.match.MatchService;
import com.typefigth.match.application.usecases.match.*;
import com.typefigth.match.domain.ports.out.match.MatchRepositoryPort;
import com.typefigth.match.infrastructure.adapters.mappers.MatchMapper;
import com.typefigth.match.infrastructure.adapters.mappers.MatchMapperAdapter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {

    @Bean
    @LoadBalanced
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Bean
    public MatchService matchService(MatchRepositoryPort matchRepositoryPort) {
        return new MatchService(
                new CreateMatchUseCaseImpl(matchRepositoryPort),
                new GetMatchUseCaseImpl(matchRepositoryPort),
                new ListMatchUseCaseImpl(matchRepositoryPort),
                new AssignOpponentToMatchUseCaseImpl(matchRepositoryPort),
                new CancelMatchUseCaseImpl(matchRepositoryPort),
                new FinishMatchUseCaseImpl(matchRepositoryPort)
        );
    }

    @Bean
    ExternalServices externalServices(WebClient webClient) {
        return new ExternalServices(webClient);
    }

    @Bean
    public MatchMapper matchMapper() {
        return new MatchMapperAdapter();
    }
}
