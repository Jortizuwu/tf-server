package com.typefigth.credentials_api.infrastructure.config;


import com.typefigth.credentials_api.application.service.CredentialsService;
import com.typefigth.credentials_api.application.usecases.GenerateTokenUseCaseImpl;
import com.typefigth.credentials_api.application.usecases.SignInUseCaseImpl;
import com.typefigth.credentials_api.application.usecases.SignUpUseCaseImpl;
import com.typefigth.credentials_api.domain.ports.out.CredentialsRepositoryPort;
import com.typefigth.credentials_api.infrastructure.adapters.JpaCredentialsRepositoryAdapter;
import com.typefigth.credentials_api.infrastructure.adapters.mapper.UserMapper;
import com.typefigth.credentials_api.infrastructure.adapters.mapper.UserMapperAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CredentialsService credentialsService(CredentialsRepositoryPort userRepositoryPort) {
        return new CredentialsService(
                new SignInUseCaseImpl(userRepositoryPort),
                new SignUpUseCaseImpl(userRepositoryPort),
                new GenerateTokenUseCaseImpl(userRepositoryPort));
    }

    @Bean
    public CredentialsRepositoryPort userRepositoryPort(JpaCredentialsRepositoryAdapter jpaCredentialsRepositoryAdapter) {
        return jpaCredentialsRepositoryAdapter;
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapperAdapter();
    }

    
}