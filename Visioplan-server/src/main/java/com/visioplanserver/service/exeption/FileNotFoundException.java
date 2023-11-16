package com.visioplanserver.service.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "File not found")
public class FileNotFoundException extends RuntimeException {
    private Long id;
    public FileNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public Long getName() {
        return id;
    }
}
