package com.typefigth.user.application.usecases.user;

import com.typefigth.user.domain.models.user.User;
import com.typefigth.user.domain.ports.in.user.GetUserUseCase;
import com.typefigth.user.domain.ports.out.user.UserRepositoryPort;

import java.util.Optional;

public class GetUserUseCaseImpl implements GetUserUseCase {
    private final UserRepositoryPort todoRepositoryPort;

    public GetUserUseCaseImpl(UserRepositoryPort todoRepositoryPort) {
        this.todoRepositoryPort = todoRepositoryPort;
    }

    @Override
    public Optional<User> getUser(String uid) {
        return todoRepositoryPort.findById(uid);
    }
}
