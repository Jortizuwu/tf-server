package com.typefigth.match.application.dtos.match;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateMatchDto {

    @NotBlank(message = "ownId must not be blank")
    @NotNull(message = "ownId must not be null")
    @NotEmpty(message = "ownId must not be empty")
    private String ownId;


    public @NotBlank(message = "ownId must not be blank") @NotNull(message = "ownId must not be null") String getOwnId() {
        return ownId;
    }

    public void setOwnId(@NotBlank(message = "ownId must not be blank") @NotNull(message = "ownId must not be null") String ownId) {
        this.ownId = ownId;
    }
}
