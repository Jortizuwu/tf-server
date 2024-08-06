package com.typefigth.user.domain.ports.in.user;

import com.typefigth.user.domain.models.user.User;

public interface CreateUserUseCase {
    User createUser(User user);
}
