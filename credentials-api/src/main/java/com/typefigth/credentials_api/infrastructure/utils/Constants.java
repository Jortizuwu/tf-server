package com.typefigth.credentials_api.infrastructure.utils;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class Constants {

    public static final String ERROR = "error";
    public static final String STATUS = "status";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";

    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";

    private Constants() {
    }
}
