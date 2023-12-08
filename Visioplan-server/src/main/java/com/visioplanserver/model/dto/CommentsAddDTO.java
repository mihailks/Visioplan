package com.visioplanserver.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class CommentsAddDTO {
    @NotBlank(message = "Text content cannot be empty")
    @Size(min = 1, max = 50, message = "Text content must be between 1 and 50 characters")
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
