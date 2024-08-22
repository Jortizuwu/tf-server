package com.typefigth.credentials_api.domain.models;

public class Payload {

    private String uid;

    public Payload(String uid) {
        this.uid = uid;
    }

    public Payload() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    //build
    public static class PayloadBuilder {
        private String uid;

        public PayloadBuilder setUid(String uid) {
            this.uid = uid;
            return this;
        }

        public Payload build() {
            return new Payload(uid);
        }
    }
}
