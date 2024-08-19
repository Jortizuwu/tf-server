package com.typefigth.quote.application.dtos.quote;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class CreateQuoteDto {

    @NotNull(message = "matchId cannot be null")
    @NotEmpty(message = "matchId cannot be empty")
    private String matchId;


    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}
