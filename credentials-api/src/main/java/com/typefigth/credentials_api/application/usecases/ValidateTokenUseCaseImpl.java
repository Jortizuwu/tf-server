package com.typefigth.credentials_api.application.usecases;

import com.typefigth.credentials_api.domain.models.Payload;
import com.typefigth.credentials_api.domain.models.Token;
import com.typefigth.credentials_api.domain.ports.in.GenerateTokenUseCase;
import com.typefigth.credentials_api.domain.ports.in.ValidateTokenUseCase;
import com.typefigth.credentials_api.domain.ports.out.CredentialsRepositoryPort;

import java.util.Map;

public class ValidateTokenUseCaseImpl implements ValidateTokenUseCase {

    private final CredentialsRepositoryPort credentialsRepositoryPort;

    public ValidateTokenUseCaseImpl(CredentialsRepositoryPort credentialsRepositoryPort) {
        this.credentialsRepositoryPort = credentialsRepositoryPort;
    }

    @Override
    public Token validate(String token) {
        return this.credentialsRepositoryPort.validate(token);
    }


}
