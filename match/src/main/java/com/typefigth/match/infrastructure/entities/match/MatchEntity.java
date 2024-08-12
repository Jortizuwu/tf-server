package com.typefigth.match.infrastructure.entities.match;

import com.typefigth.match.domain.models.enun.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "own_id", nullable = false)
    private String ownId;

    @Column(name = "opponent_id")
    private String opponentId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    private void prePersist() {
        this.status = Status.CREATED;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public MatchEntity(String id, String ownId, String opponentId, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.ownId = ownId;
        this.status = status;
        this.opponentId = opponentId;
    }

    public MatchEntity() {
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


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(String opponentId) {
        this.opponentId = opponentId;
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
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String ownId;
        private String opponentId;
        private Status status;

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

        public MatchEntity build() {
            return new MatchEntity(id, ownId, opponentId, status, createdAt, updatedAt);
        }
    }
}
