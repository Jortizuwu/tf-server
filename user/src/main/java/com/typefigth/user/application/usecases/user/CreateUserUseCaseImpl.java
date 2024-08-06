package com.typefigth.user.application.usecases.user;

import com.typefigth.user.domain.models.user.User;
import com.typefigth.user.domain.ports.in.user.CreateUserUseCase;
import com.typefigth.user.domain.ports.out.user.UserRepositoryPort;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public CreateUserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User createUser(User user) {
        return this.userRepositoryPort.save(user);
    }
}
