package com.typefigth.credentials_api.domain.ports.out;

import com.typefigth.credentials_api.domain.models.Payload;
import com.typefigth.credentials_api.domain.models.Token;
import com.typefigth.credentials_api.domain.models.User;

import java.util.Map;
import java.util.Optional;

public interface CredentialsRepositoryPort {

    User SignUp(User user);

    Optional<User> SignIn(String email, String password);

    Map<String, String> generateToken(Payload payload);

    Token validate(String token);
}
