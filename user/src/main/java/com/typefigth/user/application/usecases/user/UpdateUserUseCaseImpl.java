package com.typefigth.user.application.usecases.user;

import com.typefigth.user.domain.models.user.User;
import com.typefigth.user.domain.ports.in.user.UpdateUserUseCase;
import com.typefigth.user.domain.ports.out.user.UserRepositoryPort;

import java.util.Optional;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public UpdateUserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Optional<User> updateUser(String uid, User user) {
        return this.userRepositoryPort.updateUser(uid, user);
    }
}
