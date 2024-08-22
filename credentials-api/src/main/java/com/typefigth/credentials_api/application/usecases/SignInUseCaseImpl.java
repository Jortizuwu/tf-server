package com.typefigth.credentials_api.application.usecases;


import com.typefigth.credentials_api.domain.models.User;
import com.typefigth.credentials_api.domain.ports.in.SignInUseCase;
import com.typefigth.credentials_api.domain.ports.out.CredentialsRepositoryPort;

import java.util.Optional;


public class SignInUseCaseImpl implements SignInUseCase {


    private final CredentialsRepositoryPort credentialsRepositoryPort;

    public SignInUseCaseImpl(CredentialsRepositoryPort credentialsRepositoryPort) {
        this.credentialsRepositoryPort = credentialsRepositoryPort;
    }


    @Override
    public Optional<User> SignIn(String email, String password) {
        return this.credentialsRepositoryPort.SignIn(email, password);
    }
}
