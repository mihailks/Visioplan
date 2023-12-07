package com.visioplanserver.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {
    EmailServiceImpl serviceToTest;
    @Mock
    private TemplateEngine mockTemplateEngine;
    @Mock
    private JavaMailSender mockJavaMailSender;

    @BeforeEach
    void setUp() {
        serviceToTest = new EmailServiceImpl(mockJavaMailSender, mockTemplateEngine);
    }
    @Test
    void sendFeedback() {

    }



}
