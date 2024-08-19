package com.typefigth.match.application.dtos.match;


import com.typefigth.match.domain.models.Quote;
import com.typefigth.match.domain.models.User;
import com.typefigth.match.domain.models.enun.Status;

import java.time.LocalDateTime;
import java.util.List;

public class MatchDto {

    private String id;
    private String ownId;
    private String opponentId;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<User> users;
    private List<Quote> quotes;

    public MatchDto(String id, String ownId, String opponentId, Status status, List<User> users, List<Quote> quotes, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.ownId = ownId;
        this.opponentId = opponentId;
        this.status = status;
        this.users = users;
        this.quotes = quotes;
    }

    public MatchDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnId() {
        return ownId;
    }

    public void setOwnId(String ownId) {
        this.ownId = ownId;
    }

    public String getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(String opponentId) {
        this.opponentId = opponentId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // builder
    public static class MatchBuilder {
        private String id;
        private String ownId;
        private String opponentId;
        private Status status;
        private List<User> users;
        private List<Quote> quotes;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public MatchBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public MatchBuilder setOwnId(String ownId) {
            this.ownId = ownId;
            return this;
        }

        public MatchBuilder setOpponentId(String opponentId) {
            this.opponentId = opponentId;
            return this;
        }

        public MatchBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public MatchBuilder setUsers(List<User> users) {
            this.users = users;
            return this;
        }

        public MatchBuilder setQuotes(List<Quote> quotes) {
            this.quotes = quotes;
            return this;
        }

        public MatchBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public MatchBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public MatchDto build() {
            return new MatchDto(id, ownId, opponentId, status, users, quotes,createdAt, updatedAt);
        }
    }

}
