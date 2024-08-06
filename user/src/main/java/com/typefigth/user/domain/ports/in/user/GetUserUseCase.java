package com.typefigth.user.domain.ports.in.user;

import com.typefigth.user.domain.models.user.User;

import java.util.Optional;

public interface GetUserUseCase {

    Optional<User> getUser(String uid);

}
