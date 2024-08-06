package com.typefigth.user.domain.ports.out.user;

import com.typefigth.user.domain.models.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findById(String id);

    List<User> findAll();

    boolean deleteById(String id);

    Optional<User> updateUser(String id, User body);

    User save(User todo);
}
