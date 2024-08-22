package com.typefigth.credentials_api.infrastructure.utils;

public class Constants {

    public static final String ERROR = "error";
    public static final String STATUS = "status";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";

    private Constants() {
    }
}
