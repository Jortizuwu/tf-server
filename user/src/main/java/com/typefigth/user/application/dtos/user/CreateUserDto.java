package com.typefigth.user.application.dtos.user;

import com.typefigth.user.infrastructure.utils.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public class CreateUserDto {

    @NotNull(message = "nickname cannot be null")
    @NotEmpty(message = "nickname cannot be empty")
    private String nickname;

    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = Constants.PASSWORD_REGEX, message = "Password must be at least 8 characters long, include at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;

    @Email(message = "Email is not valid")
    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    // Getters y Setters
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
