package com.typefigth.user.application.services.user;

import com.typefigth.user.domain.models.user.User;
import com.typefigth.user.domain.ports.in.user.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements CreateUserUseCase, GetUserUseCase, ListUserUseCase, UpdateUserUseCase, DeleteUserUseCase {
    private final CreateUserUseCase createUserUseCase;

    private final GetUserUseCase getUserUseCase;

    private final ListUserUseCase listUserUseCase;

    private final UpdateUserUseCase updateUserUseCase;

    private final DeleteUserUseCase deleteUserUseCase;

    public UserService(CreateUserUseCase createUserUseCase, GetUserUseCase getUserUseCase, ListUserUseCase listUserUseCase, UpdateUserUseCase updateUserUseCase, DeleteUserUseCase deleteUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.listUserUseCase = listUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @Override
    public User createUser(User body) {
        return this.createUserUseCase.createUser(body);
    }

    @Override
    public boolean deleteUser(String id) {
        return this.deleteUserUseCase.deleteUser(id);
    }

    @Override
    public Optional<User> getUser(String id) {
        return this.getUserUseCase.getUser(id);
    }

    @Override
    public List<User> listUsers() {
        return this.listUserUseCase.listUsers();
    }

    @Override
    public Optional<User> updateUser(String id, User body) {
        return this.updateUserUseCase.updateUser(id, body);
    }
}


