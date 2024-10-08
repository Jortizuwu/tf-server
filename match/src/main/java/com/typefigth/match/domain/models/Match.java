package com.typefigth.match.domain.models;

import java.time.LocalDateTime;

public class Match {

    private String id;
    private String ownId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Match(String id, String ownId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.ownId = ownId;
    }

    public Match() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOwnId() {
        return ownId;
    }

    public void setOwnId(String ownId) {
        this.ownId = ownId;
    }

    // builder
    public static class MatchBuilder {
        private String id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String ownId;

        public MatchBuilder setId(String id) {
            this.id = id;
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

        public MatchBuilder setOwnId(String ownId) {
            this.ownId = ownId;
            return this;
        }

        public Match build() {
            return new Match(id, ownId, createdAt, updatedAt);
        }
    }
}
