package com.typefigth.match.application.dtos.match;


import com.typefigth.match.domain.models.User;

import java.time.LocalDateTime;

public class MatchDto {

    private String id;
    private String ownId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User own;

    public MatchDto(String id, String ownId, User own, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.ownId = ownId;
        this.own = own;
    }

    public MatchDto() {
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

    public User getOwn() {
        return own;
    }

    public void setOwn(User own) {
        this.own = own;
    }

    // builder
    public static class MatchBuilder {
        private String id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String ownId;
        private User own;

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


        public MatchBuilder setOwn(User own) {
            this.own = own;
            return this;
        }

        public MatchDto build() {
            return new MatchDto(id, ownId, own, createdAt, updatedAt);
        }
    }

}
