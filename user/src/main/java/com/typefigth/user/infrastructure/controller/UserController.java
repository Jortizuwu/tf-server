package com.typefigth.user.infrastructure.controller;

import com.typefigth.user.application.dtos.user.CreateUserDto;
import com.typefigth.user.application.dtos.user.UpdateUserDto;
import com.typefigth.user.application.dtos.user.UserDto;
import com.typefigth.user.application.services.user.UserService;
import com.typefigth.user.domain.models.user.User;
import com.typefigth.user.domain.models.user.enun.Status;
import com.typefigth.user.infrastructure.adapters.user.mapper.UserMapper;
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
    private final UserMapper userMapper;

    private static final String USER_NOT_FOUND = "User with id %s not found";

    public UserController(UserService userServices, JpaUserRepository jpaUserRepository, UserMapper userMapper) {
        this.userServices = userServices;
        this.jpaUserRepository = jpaUserRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    @GetMapping()
    public ResponseEntity<List<UserDto>> listUsers() {
        List<User> users = this.userServices.listUsers();

        List<UserDto> usersDto = users.stream().map(userMapper::fromUser).toList();

        return ResponseEntity.status(HttpStatus.OK).body(usersDto);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id) {
        User user = this.userServices.getUser(id).orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND, id)));

        UserDto userDto = userMapper.fromUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @Transactional()
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserDto body) {

        User user = new User();
        user.setNickname(body.getNickname());
        user.setPassword(body.getPassword());
        user.setEmail(body.getEmail());
        user.setName(body.getName());

        return ResponseEntity.status(HttpStatus.OK).body(userMapper.fromUser(this.userServices.createUser(user)));
    }

    @Transactional()
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UpdateUserDto body, @PathVariable String id) {

        User userDb = this.userServices.getUser(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND, id)));

        if (userDb.getStatus().equals(Status.INACTIVE)) {
            throw new ResourceNotFoundException(String.format("error user with id: %s is inactive", id));
        }

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
        if (body.getStatus() != null) userDb.setStatus(body.getStatus());

        User userUpdated = this.userServices.updateUser(id, userDb)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND, id)));

        return ResponseEntity.ok(this.userMapper.fromUser(userUpdated));
    }

    @Transactional()
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        
        User userDb = this.userServices.getUser(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND, id)));

        if (userDb.getStatus().equals(Status.INACTIVE)) {
            throw new ResourceNotFoundException(String.format("error user with id: %s is inactive", id));
        }

        this.userServices.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}