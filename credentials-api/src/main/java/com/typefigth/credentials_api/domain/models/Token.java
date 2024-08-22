package com.typefigth.credentials_api.domain.models;

import java.time.LocalDateTime;

public class Token {

    private String token;
    private LocalDateTime expirationDate;


    public Token(String token, LocalDateTime expirationDate) {
        this.token = token;
        this.expirationDate = expirationDate;
    }

    public Token() {

    }


    public String getToken() {
        return token;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public static class TokenBuilder {
        private String token;
        private LocalDateTime expirationDate;

        public TokenBuilder setToken(String token) {
            this.token = token;
            return this;
        }

        public TokenBuilder setExpirationDate(LocalDateTime expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public Token build() {
            return new Token(token, expirationDate);
        }
    }
}
