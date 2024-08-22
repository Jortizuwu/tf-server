package com.typefigth.credentials_api.application.dtos;


import com.typefigth.credentials_api.domain.models.Token;
import com.typefigth.credentials_api.domain.models.enun.Status;

import java.time.LocalDateTime;

public class CredentialsDto {

    private String uid;
    private String nickname;
    private String email;
    private String name;
    private Status status;
    private Token token;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CredentialsDto(String uid, String nickname, String email, String name, Status status, Token token, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.uid = uid;
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.status = status;
        this.token = token;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CredentialsDto() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    // build
    public static class UserDtoBuilder {

        private String uid;
        private String nickname;
        private String email;
        private String name;
        private Status status;
        private Token token;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public UserDtoBuilder setUid(String uid) {
            this.uid = uid;
            return this;
        }

        public UserDtoBuilder setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserDtoBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserDtoBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public UserDtoBuilder setToken(Token token) {
            this.token = token;
            return this;
        }

        public UserDtoBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserDtoBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }


        public CredentialsDto build() {
            return new CredentialsDto(uid, nickname, email, name, status, token, createdAt, updatedAt);
        }

    }
}
