package com.typefigth.api_gateway.application.dtos;

import java.time.LocalDateTime;

public class TokenDto {

    private String token;
    private LocalDateTime expirationDate;


    public TokenDto(String token, LocalDateTime expirationDate) {
        this.token = token;
        this.expirationDate = expirationDate;
    }

    public TokenDto() {

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

        public TokenDto build() {
            return new TokenDto(token, expirationDate);
        }
    }
}
