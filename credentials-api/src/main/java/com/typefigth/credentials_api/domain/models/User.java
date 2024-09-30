package com.typefigth.credentials_api.domain.models;

import com.typefigth.credentials_api.domain.models.enun.Status;

import java.time.LocalDateTime;

public class User {

    private String uid;
    private String nickname;
    private String password;
    private String email;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String uid, String nickname, String password, String email, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.uid = uid;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // builder
    public static class UserBuilder {
        private String uid;
        private String nickname;
        private String password;
        private String email;
        private Status status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public UserBuilder setUid(String uid) {
            this.uid = uid;
            return this;
        }

        public UserBuilder setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }


        public UserBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public UserBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public User build() {
            return new User(uid, nickname, password, email, status, createdAt, updatedAt);
        }
    }

}
