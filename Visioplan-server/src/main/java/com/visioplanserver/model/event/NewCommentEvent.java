package com.visioplanserver.model.event;

import org.springframework.context.ApplicationEvent;

public class NewCommentEvent extends ApplicationEvent {
    String username;
    String email;

    public NewCommentEvent(Object source, String username, String email) {
        super(source);
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
