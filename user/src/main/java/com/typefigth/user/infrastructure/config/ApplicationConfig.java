package com.typefigth.user.infrastructure.config;

import com.typefigth.user.application.services.user.UserService;
import com.typefigth.user.application.usecases.user.*;
import com.typefigth.user.domain.ports.out.user.UserRepositoryPort;
import com.typefigth.user.infrastructure.adapters.user.JpaUserRepositoryAdapter;
import com.typefigth.user.infrastructure.adapters.user.mapper.UserMapper;
import com.typefigth.user.infrastructure.adapters.user.mapper.UserMapperAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserService userServices(UserRepositoryPort userRepositoryPort) {
        return new UserService(
                new CreateUserUseCaseImpl(userRepositoryPort),
                new GetUserUseCaseImpl(userRepositoryPort),
                new ListUserUseCaseImpl(userRepositoryPort),
                new UpdateUserUseCaseImpl(userRepositoryPort),
                new DeleteUserUseCaseImpl(userRepositoryPort));
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(JpaUserRepositoryAdapter jpaUserRepositoryAdapter) {
        return jpaUserRepositoryAdapter;
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapperAdapter();
    }


}