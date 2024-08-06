package com.typefigth.user.application.usecases.user;

import com.typefigth.user.domain.models.user.User;
import com.typefigth.user.domain.ports.in.user.ListUserUseCase;
import com.typefigth.user.domain.ports.out.user.UserRepositoryPort;

import java.util.List;

public class ListUserUseCaseImpl implements ListUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    ListUserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public List<User> listUsers() {
        return this.userRepositoryPort.findAll();
    }
}
