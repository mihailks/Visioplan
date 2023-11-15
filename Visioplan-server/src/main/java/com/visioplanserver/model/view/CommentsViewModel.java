package com.visioplanserver.model.view;

import java.time.LocalDateTime;

public class CommentsViewModel {
    private Long commentsId;
    private String textContent;
    private String author;
    private LocalDateTime created;
    private Long file;

    public CommentsViewModel() {
    }
    public CommentsViewModel(Long commentsId, String textContent, String author, Long file, LocalDateTime created) {
        this.commentsId = commentsId;
        this.textContent = textContent;
        this.author = author;
        this.file = file;
        this.created = created;
    }

    public Long getCommentsId() {
        return commentsId;
    }

    public CommentsViewModel setCommentsId(Long commentsId) {
        this.commentsId = commentsId;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentsViewModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CommentsViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public CommentsViewModel setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public Long getFile() {
        return file;
    }

    public CommentsViewModel setFile(Long file) {
        this.file = file;
        return this;
    }
}
