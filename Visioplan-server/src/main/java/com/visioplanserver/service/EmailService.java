package com.visioplanserver.service;

import com.visioplanserver.model.event.UserRegisterEvent;

public interface EmailService {

    void sendFeedback(String name, String email, String feedback);

    void sendWelcomeEmail(UserRegisterEvent event);

}
