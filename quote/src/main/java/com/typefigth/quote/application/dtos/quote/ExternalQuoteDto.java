package com.typefigth.quote.application.dtos.quote;

import java.time.LocalDate;

public class ExternalQuoteDto {

    private String content;
    private String author;
    private String[] tags;
    private String authorSlug;
    private long length;
    private LocalDate dateAdded;
    private LocalDate dateModified;


    ExternalQuoteDto(String content, String author, String[] tags, String authorSlug, long length, LocalDate dateAdded, LocalDate dateModified) {
        this.content = content;
        this.author = author;
        this.tags = tags;
        this.authorSlug = authorSlug;
        this.length = length;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
    }

    ExternalQuoteDto() {
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

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getAuthorSlug() {
        return authorSlug;
    }

    public void setAuthorSlug(String authorSlug) {
        this.authorSlug = authorSlug;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }
}

