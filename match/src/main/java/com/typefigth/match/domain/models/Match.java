package com.typefigth.match.domain.models;

import com.typefigth.match.domain.models.enun.Status;

import java.time.LocalDateTime;

public class Match {

    private String id;
    private String ownId;
    private String opponentId;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Match(String id, String ownId, String opponentId, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.ownId = ownId;
        this.opponentId = opponentId;
        this.status = status;
    }

    public Match() {
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

        public MatchBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public MatchBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }


        public Match build() {
            return new Match(id, ownId, opponentId, status, createdAt, updatedAt);
        }
    }
}
