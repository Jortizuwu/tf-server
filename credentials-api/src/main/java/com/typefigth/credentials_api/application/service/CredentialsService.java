package com.typefigth.credentials_api.application.service;


import com.typefigth.credentials_api.domain.models.Payload;
import com.typefigth.credentials_api.domain.models.User;
import com.typefigth.credentials_api.domain.ports.in.GenerateTokenUseCase;
import com.typefigth.credentials_api.domain.ports.in.SignInUseCase;
import com.typefigth.credentials_api.domain.ports.in.SignUpUseCase;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
// todo: add validation token
public class CredentialsService implements SignInUseCase, SignUpUseCase, GenerateTokenUseCase {
    private final SignInUseCase signInUseCase;
    private final SignUpUseCase signUpUseCase;
    private final GenerateTokenUseCase generateTokenUseCase;


    public CredentialsService(SignInUseCase signInUseCase, SignUpUseCase signUpUseCase, GenerateTokenUseCase generateTokenUseCase) {
        this.signInUseCase = signInUseCase;
        this.signUpUseCase = signUpUseCase;
        this.generateTokenUseCase = generateTokenUseCase;
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
}


