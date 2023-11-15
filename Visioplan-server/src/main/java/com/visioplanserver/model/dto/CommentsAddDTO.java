package com.visioplanserver.model.dto;

import java.time.LocalDateTime;

public class CommentsAddDTO {
    private String textContent;

    public CommentsAddDTO() {
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentsAddDTO setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }
}
