package com.visioplanserver.service;

import com.visioplanserver.model.event.UserRegisterEvent;
import com.visioplanserver.model.view.CommentsViewModel;

import java.util.List;

public interface EmailService {

    void sendFeedback(String name, String email, String feedback);

    void sendWelcomeEmail(UserRegisterEvent event);

    void sendNewCommentsEmail(List<CommentsViewModel> commentsEntities);
}
