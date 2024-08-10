package com.typefigth.user.infrastructure.controller;

import com.typefigth.user.application.services.user.UserService;
import com.typefigth.user.domain.models.user.User;
import com.typefigth.user.infrastructure.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userServices;

    public UserController(UserService userServices) {
        this.userServices = userServices;
    }

    @GetMapping()
    public ResponseEntity<List<User>> listUsers(HttpServletRequest request) {
        List<User> users = this.userServices.listUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id, HttpServletRequest request) {
        User user = this.userServices.getUser(id).orElseThrow(() -> new ResourceNotFoundException(String.format("error user with id: %s not found", id)));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User body, HttpServletRequest request) {
        User newUser = this.userServices.createUser(body);
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }
}