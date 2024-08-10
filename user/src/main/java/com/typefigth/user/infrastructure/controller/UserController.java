package com.typefigth.user.infrastructure.controller;

import com.typefigth.user.application.dtos.user.CreateUserDto;
import com.typefigth.user.application.services.user.UserService;
import com.typefigth.user.domain.models.user.User;
import com.typefigth.user.infrastructure.exceptions.ResourceNotFoundException;
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

    public UserController(UserService userServices) {
        this.userServices = userServices;
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
        User user = this.userServices.getUser(id).orElseThrow(() -> new ResourceNotFoundException(String.format("error user with id: %s not found", id)));
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
}