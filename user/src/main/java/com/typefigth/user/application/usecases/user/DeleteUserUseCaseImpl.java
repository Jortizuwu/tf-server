package com.typefigth.user.application.usecases.user;

import com.typefigth.user.domain.ports.in.user.DeleteUserUseCase;
import com.typefigth.user.domain.ports.out.user.UserRepositoryPort;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    DeleteUserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public boolean deleteUser(String uid) {
        return this.userRepositoryPort.deleteById(uid);
    }
}
