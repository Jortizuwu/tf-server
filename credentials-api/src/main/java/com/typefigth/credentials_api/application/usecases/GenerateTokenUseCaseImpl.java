package com.typefigth.credentials_api.application.usecases;

import com.typefigth.credentials_api.domain.models.Payload;
import com.typefigth.credentials_api.domain.ports.in.GenerateTokenUseCase;
import com.typefigth.credentials_api.domain.ports.out.CredentialsRepositoryPort;

import java.util.Map;

public class GenerateTokenUseCaseImpl implements GenerateTokenUseCase {

    private final CredentialsRepositoryPort credentialsRepositoryPort;

    public GenerateTokenUseCaseImpl(CredentialsRepositoryPort credentialsRepositoryPort) {
        this.credentialsRepositoryPort = credentialsRepositoryPort;
    }

    @Override
    public Map<String, String> generateToken(Payload payload) {
        return this.credentialsRepositoryPort.generateToken(payload);
    }

}
