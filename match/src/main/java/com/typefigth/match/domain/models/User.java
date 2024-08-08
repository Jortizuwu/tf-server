package com.typefigth.match.domain.models;

public class User {

    private String uid;
    private String nickname;

    public User(String uid, String nickname) {
        this.uid = uid;
        this.nickname = nickname;
    }

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    // builder
    public static class UserBuilder {
        private String uid;
        private String nickname;

        public UserBuilder setUid(String uid) {
            this.uid = uid;
            return this;
        }

        public UserBuilder setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }


        public User build() {
            return new User(uid, nickname);
        }
    }

}
