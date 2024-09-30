package com.typefigth.user.application.dtos.user;

import com.typefigth.user.domain.models.user.enun.Status;
import com.typefigth.user.infrastructure.utils.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public class UpdateUserDto {

    private String nickname;

    @Pattern(regexp = Constants.PASSWORD_REGEX, message = "Password must be at least 8 characters long, include at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;

    @Email(message = "Email is not valid")
    private String email;

    private Status status;

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

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
