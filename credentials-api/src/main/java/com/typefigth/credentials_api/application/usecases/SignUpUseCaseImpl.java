package com.typefigth.credentials_api.application.usecases;


import com.typefigth.credentials_api.domain.models.User;
import com.typefigth.credentials_api.domain.ports.in.SignUpUseCase;
import com.typefigth.credentials_api.domain.ports.out.CredentialsRepositoryPort;


public class SignUpUseCaseImpl implements SignUpUseCase {

    private final CredentialsRepositoryPort credentialsRepositoryPort;

    public SignUpUseCaseImpl(CredentialsRepositoryPort credentialsRepositoryPort) {
        this.credentialsRepositoryPort = credentialsRepositoryPort;
    }

    @Override
    public User SignUp(User user) {
        return this.credentialsRepositoryPort.SignUp(user);
    }
}
