package com.typefigth.user.application.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateUserDto {

    private static final String PASSWORD_REG = "/^\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*[A-Z])(?=\\S*[a-z])(?=\\S*[!@#$%^&*? ])\\S*$/";
    private static final String EMAIL_REG = "/^(([^<>()[\\]\\\\.,;:\\s@\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$/";

    @NotNull(message = "nickname cannot be null")
    @NotEmpty(message = "nickname cannot be empty")
    private String nickname;

    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = PASSWORD_REG, message = "Password must be at least 8 characters long, include at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;

    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    @Pattern(regexp = EMAIL_REG, message = "Email must be a valid email address")
    private String email;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
