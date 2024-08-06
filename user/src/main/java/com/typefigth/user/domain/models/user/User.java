package com.typefigth.user.domain.models.user;

public class User {

    private String uid;
    private String nickname;
    private String password;
    private String email;
    private String name;

    public User(String uid, String nickname, String password, String email, String name) {
        this.uid = uid;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.name = name;
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

    public String getName() {
        return name;
    }

    // builder
    public static class UserBuilder {
        private String uid;
        private String nickname;
        private String password;
        private String email;
        private String name;

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

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public User build() {
            return new User(uid, nickname, password, email, name);
        }
    }

}
