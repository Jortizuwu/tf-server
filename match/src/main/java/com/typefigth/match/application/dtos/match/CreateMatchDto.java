package com.typefigth.match.application.dtos.match;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateMatchDto {

    @NotBlank(message = "ownId must not be blank")
    @NotNull(message = "ownId must not be null")
    @NotEmpty(message = "ownId must not be empty")
    private String ownId;


    public String getOwnId() {
        return ownId;
    }

    public void setOwnId(String ownId) {
        this.ownId = ownId;
    }
}
