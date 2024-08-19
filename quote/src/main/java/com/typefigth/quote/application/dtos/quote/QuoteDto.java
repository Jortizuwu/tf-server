package com.typefigth.quote.application.dtos.quote;

import com.typefigth.quote.domain.models.quote.Quote;

import java.time.LocalDateTime;

public class QuoteDto {

    private String id;
    private String quoteId;
    private String matchId;
    private String content;
    private String author;
    private int length;

    public QuoteDto() {
    }

    public QuoteDto(String id, String quoteId, String matchId, String content, String author, int length) {
        this.id = id;
        this.quoteId = quoteId;
        this.matchId = matchId;
        this.content = content;
        this.author = author;
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    // builder
    public static class Builder {
        private String id;
        private String quoteId;
        private String matchId;
        private String content;
        private String author;
        private int length;


        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder quoteId(String quoteId) {
            this.quoteId = quoteId;
            return this;
        }

        public Builder matchId(String matchId) {
            this.matchId = matchId;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public QuoteDto build() {
            return new QuoteDto(id, quoteId, matchId, content, author, length);
        }
    }
}
