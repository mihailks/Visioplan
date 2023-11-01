package com.visioplanserver.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentsEntity extends BaseEntity{
    @ManyToOne
    private UserEntity author;
    @Column(nullable = false)
    private String textContent;
    @Column(nullable = false)
    private LocalDateTime created;

    public CommentsEntity() {
    }

    public UserEntity getAuthor() {
        return author;
    }

    public CommentsEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentsEntity setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public CommentsEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

}
