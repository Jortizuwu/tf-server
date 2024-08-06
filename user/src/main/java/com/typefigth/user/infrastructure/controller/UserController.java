package com.typefigth.user.infrastructure.controller;

import com.typefigth.user.application.response.Response;
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
    public ResponseEntity<Response> listUsers(HttpServletRequest request) {
        List<User> user = this.userServices.listUsers();
        Response response = Response.build(200, request.getContextPath(), true, user, false, getClientIp(request), "OK", HttpStatus.OK.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getUser(@PathVariable String id, HttpServletRequest request) {
        User user = this.userServices.getUser(id).orElseThrow(() -> new ResourceNotFoundException(String.format("error user with id: %s not found", id)));
        Response response = Response.build(200, request.getContextPath(), true, user, false, getClientIp(request), "OK", HttpStatus.OK.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody User body, HttpServletRequest request) {
        User newuser = this.userServices.createUser(body);
        Response response = Response.build(200, request.getContextPath(), true, newuser, false, getClientIp(request), "OK", HttpStatus.OK.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || remoteAddr.isEmpty()) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

}