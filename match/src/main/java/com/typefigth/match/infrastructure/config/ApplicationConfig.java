package com.typefigth.match.infrastructure.config;

import com.typefigth.match.application.services.match.MatchService;
import com.typefigth.match.application.usecases.match.CreateMatchUseCaseImpl;
import com.typefigth.match.application.usecases.match.GetMatchUseCaseImpl;
import com.typefigth.match.application.usecases.match.ListMatchUseCaseImpl;
import com.typefigth.match.domain.ports.out.match.MatchRepositoryPort;
import com.typefigth.match.infrastructure.adapters.JpaMatchRepositoryAdapter;
import com.typefigth.match.infrastructure.adapters.mappers.MatchMapper;
import com.typefigth.match.infrastructure.adapters.mappers.MatchMapperAdapter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class ApplicationConfig {

    @Bean
    public MatchService matchService(MatchRepositoryPort matchRepositoryPort) {
        return new MatchService(
                new CreateMatchUseCaseImpl(matchRepositoryPort),
                new GetMatchUseCaseImpl(matchRepositoryPort),
                new ListMatchUseCaseImpl(matchRepositoryPort));
    }

    @Bean
    public MatchRepositoryPort matchRepository(JpaMatchRepositoryAdapter jpaMatchRepositoryAdapter) {
        return jpaMatchRepositoryAdapter;
    }

    @Bean
    public MatchMapper matchMapper() {
        return new MatchMapperAdapter();
    }
}
