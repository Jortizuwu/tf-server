package com.typefigth.credentials_api.domain.ports.in;

import com.typefigth.credentials_api.domain.models.User;

import java.util.Optional;

public interface SignInUseCase {

    Optional<User> SignIn(String email, String password);
}
