package com.visioplanserver.web;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.visioplanserver.testUtils.ItUserTestDataUtil;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FeedbackControllerTest {
    private static final String TEST_USER_OWNER_NAME = "user";
    @Autowired
    private ItUserTestDataUtil itUserTestDataUtil;
    @Autowired
    private MockMvc mockMvc;

    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private int port;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;

    private GreenMail greenMail;

    @BeforeEach
    void setUp() {
        greenMail = new GreenMail(new ServerSetup(port, host, "smtp"));
        greenMail.start();
        greenMail.setUser(username, password);

    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
        itUserTestDataUtil.cleanUp();
    }

    @Test
    void testFeedbackAnonymous() throws Exception {
        mockMvc.perform(
                        get("/feedback")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testFeedbackWrongInput() throws Exception {
        mockMvc.perform(
                        get("/feedback")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("feedback"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testFeedback() throws Exception {
        mockMvc.perform(
                        post("/feedback")
                                .param("name", "test")
                                .param("email", "test@gmail.com")
                                .param("feedback", "testFeedback")
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
        greenMail.waitForIncomingEmail(1);

        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();

        Assertions.assertEquals(1, receivedMessages.length);

        MimeMessage receivedMessage = receivedMessages[0];
        Assertions.assertTrue(receivedMessage.getContent().toString().contains("test"));
        Assertions.assertTrue(receivedMessage.getContent().toString().contains("testFeedback"));
        Assertions.assertEquals(1, receivedMessage.getAllRecipients().length);
        Assertions.assertEquals("feedback@visioplan.com", receivedMessage.getAllRecipients()[0].toString());

    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testSendFeedbackUser() throws Exception {
        itUserTestDataUtil.createTestUser(TEST_USER_OWNER_NAME);
        mockMvc.perform(
                        post("/feedback")
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("feedback"));
    }

    @Test
    void testContactAnonymous() throws Exception {
        mockMvc.perform(
                        get("/contact")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("contact"));
    }

    @Test
    void testContactPostShouldWork() throws Exception {
        mockMvc.perform(
                        post("/contact")
                                .param("name", "test")
                                .param("email", "test@email")
                                .param("feedback", "testTheService")
                                .with(csrf())

                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        greenMail.waitForIncomingEmail(1);

        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();

        Assertions.assertEquals(1, receivedMessages.length);

        MimeMessage receivedMessage = receivedMessages[0];
        Assertions.assertTrue(receivedMessage.getContent().toString().contains("test"));
        Assertions.assertTrue(receivedMessage.getContent().toString().contains("testTheService"));
        Assertions.assertEquals(1, receivedMessage.getAllRecipients().length);
        Assertions.assertEquals("feedback@visioplan.com", receivedMessage.getAllRecipients()[0].toString());
    }

    @Test
    void testContactPostNoInput() throws Exception {
        mockMvc.perform(
                        post("/contact")
                                .with(csrf())

                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("contact"));
    }

    @Test
    void testContactPostRedirect() throws Exception {
        mockMvc.perform(
                        post("/contact")
                                .with(csrf())

                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("contact"));
    }


}
