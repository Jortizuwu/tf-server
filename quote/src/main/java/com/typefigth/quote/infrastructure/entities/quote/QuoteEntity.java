package com.typefigth.quote.infrastructure.entities.quote;


import jakarta.persistence.*;

@Entity
@Table(name = "quotes")
public class QuoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "match_id", nullable = false, length = 36)
    private String matchId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "author")
    private String author;

    @Column(name = "length", nullable = false)
    private int length;


    public QuoteEntity(String id, String matchId, String content, String author, int length) {
        this.id = id;
        this.matchId = matchId;
        this.content = content;
        this.author = author;
        this.length = length;
    }

    public QuoteEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    // build
    public static class QuiteEntityBuilder {

        private String id;
        private String quoteId;
        private String content;
        private String author;
        private int length;


        public QuiteEntityBuilder id(String id) {
            this.id = id;
            return this;
        }


        public QuiteEntityBuilder quoteId(String quoteId) {
            this.quoteId = quoteId;
            return this;
        }

        public QuiteEntityBuilder content(String content) {
            this.content = content;
            return this;
        }


        public QuiteEntityBuilder author(String author) {
            this.author = author;
            return this;
        }


        public QuiteEntityBuilder length(int length) {
            this.length = length;
            return this;
        }


        public QuoteEntity build() {
            return new QuoteEntity(id, quoteId, content, author, length);
        }
    }
}
