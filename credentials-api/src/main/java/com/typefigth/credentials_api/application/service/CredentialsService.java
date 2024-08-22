package com.typefigth.credentials_api.application.service;


import com.typefigth.credentials_api.domain.models.Payload;
import com.typefigth.credentials_api.domain.models.Token;
import com.typefigth.credentials_api.domain.models.User;
import com.typefigth.credentials_api.domain.ports.in.GenerateTokenUseCase;
import com.typefigth.credentials_api.domain.ports.in.SignInUseCase;
import com.typefigth.credentials_api.domain.ports.in.SignUpUseCase;
import com.typefigth.credentials_api.domain.ports.in.ValidateTokenUseCase;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;

@Service
public class CredentialsService implements SignInUseCase, SignUpUseCase, GenerateTokenUseCase, ValidateTokenUseCase {
    private final SignInUseCase signInUseCase;
    private final SignUpUseCase signUpUseCase;
    private final GenerateTokenUseCase generateTokenUseCase;
    private final ValidateTokenUseCase validateTokenUseCase;


    public CredentialsService(SignInUseCase signInUseCase, SignUpUseCase signUpUseCase, GenerateTokenUseCase generateTokenUseCase, ValidateTokenUseCase validateTokenUseCase) {
        this.signInUseCase = signInUseCase;
        this.signUpUseCase = signUpUseCase;
        this.generateTokenUseCase = generateTokenUseCase;
        this.validateTokenUseCase = validateTokenUseCase;
    }


    @Override
    public Optional<User> SignIn(String email, String password) {
        return this.signInUseCase.SignIn(email, password);
    }

    @Override
    public User SignUp(User user) {
        return this.signUpUseCase.SignUp(user);
    }

    @Override
    public Map<String, String> generateToken(Payload payload) {
        return this.generateTokenUseCase.generateToken(payload);
    }

    @Override
    public Token validate(String token) {
        return this.validateTokenUseCase.validate(token);
    }
}


