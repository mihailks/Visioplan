package com.visioplanserver.service.impl;

import com.visioplanserver.model.event.UserRegisterEvent;
import com.visioplanserver.model.view.CommentsViewModel;
import com.visioplanserver.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;


@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendFeedback(String name, String email, String feedback) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo("feedback@visioplan.com");
            mimeMessageHelper.setFrom(email);
            mimeMessageHelper.setSubject("New feedback from " + name);
            mimeMessageHelper.setText(feedback);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @EventListener(UserRegisterEvent.class)
    @Override
    public void sendWelcomeEmail(UserRegisterEvent event) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(event.getEmail());
            mimeMessageHelper.setFrom("visioplan@support.com");
            mimeMessageHelper.setSubject("Welcome to VisioPlan!");
            mimeMessageHelper.setText(generateWelcomeEmailBody(event.getUsername()), true);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void sendNewCommentsEmail(List<CommentsViewModel> commentsEntities) {
        commentsEntities.forEach(this::sendNewCommentsEmail);
    }

    private void sendNewCommentsEmail(CommentsViewModel commentsViewModel) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(commentsViewModel.getUploaderEmail());
            mimeMessageHelper.setFrom("visioplan@support.com");
            mimeMessageHelper.setSubject("Welcome to VisioPlan!");
            mimeMessageHelper.setText(generateNewCommentsEmailBody(List.of(commentsViewModel)), true);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNewCommentsEmailBody(List<CommentsViewModel> commentsViewModels) {
        Context context = new Context();
        context.setVariable("comments", commentsViewModels);
        return templateEngine.process("email/new-comments-email", context);
    }

    private String generateWelcomeEmailBody(String username) {
        Context context = new Context();
        context.setVariable("username", username);
        return templateEngine.process("email/new-user-email", context);
    }

}


