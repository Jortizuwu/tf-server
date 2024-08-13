package com.typefigth.match.application.dtos.match;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AssignMatchDto {


    @NotBlank(message = "opponentId must not be blank")
    @NotNull(message = "opponentId must not be null")
    @NotEmpty(message = "opponentId must not be empty")
    private String opponentId;

    public String getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(String opponentId) {
        this.opponentId = opponentId;
    }

}
