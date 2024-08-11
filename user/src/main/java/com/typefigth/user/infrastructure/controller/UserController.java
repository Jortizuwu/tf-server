package com.typefigth.user.infrastructure.controller;

import com.typefigth.user.application.dtos.user.CreateUserDto;
import com.typefigth.user.application.dtos.user.UpdateUserDto;
import com.typefigth.user.application.services.user.UserService;
import com.typefigth.user.domain.models.user.User;
import com.typefigth.user.infrastructure.exceptions.DuplicatedValueException;
import com.typefigth.user.infrastructure.exceptions.ResourceNotFoundException;
import com.typefigth.user.infrastructure.repository.JpaUserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userServices;
    private final JpaUserRepository jpaUserRepository;

    private final String USER_NOT_FOUND = "User with id %s not found";

    public UserController(UserService userServices, JpaUserRepository jpaUserRepository) {
        this.userServices = userServices;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Transactional(readOnly = true)
    @GetMapping()
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = this.userServices.listUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User user = this.userServices.getUser(id).orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND, id)));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Transactional()
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserDto body) {

        User user = new User();
        user.setNickname(body.getNickname());
        user.setPassword(body.getPassword());
        user.setEmail(body.getEmail());
        user.setName(body.getName());

        return ResponseEntity.status(HttpStatus.OK).body(this.userServices.createUser(user));
    }

    @Transactional()
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UpdateUserDto body, @PathVariable String id) {

        User userDb = this.userServices.getUser(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND, id)));

        if (body.getNickname() != null &&
                !body.getNickname().equals(userDb.getNickname()) &&
                this.jpaUserRepository.existsByNickname(body.getNickname())) {
            throw new DuplicatedValueException(String.format("error user with nickname: %s is already registered", body.getNickname()));
        }

        if (body.getEmail() != null &&
                !body.getEmail().equals(userDb.getEmail()) &&
                this.jpaUserRepository.existsByEmail(body.getEmail())) {
            throw new DuplicatedValueException(String.format("error user with email: %s is already registered", body.getEmail()));
        }

        if (body.getNickname() != null) userDb.setNickname(body.getNickname());
        if (body.getEmail() != null) userDb.setEmail(body.getEmail());
        if (body.getName() != null) userDb.setName(body.getName());
        if (body.getPassword() != null) userDb.setPassword(body.getPassword());

        User userUpdated = this.userServices.updateUser(id, userDb)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND, id)));

        return ResponseEntity.ok(userUpdated);
    }
}