package com.visioplanserver.web;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserRegisterControllerTestIT {

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
    void SetUp() {
        greenMail = new GreenMail(new ServerSetup(port, host, "smtp"));
        greenMail.start();
        greenMail.setUser(username, password);
    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
    }

    @Test
    void testRegistration() throws Exception {
        String baseUrl = "http://localhost";
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user/register")
                                .param("username", "Ivan")
                                .param("email", "ivan@hotmail.com")
                                .param("password", "ivan123")
                                .param("confirmPassword", "ivan123")
                                .param("firstName", "Ivan")
                                .param("lastName", "Ivanov")
                                .param("companyName", "Eiffage")
                                .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));
//                .andExpect(view().name("redirect:users/user-login"));
    }

//    @Test
//    void testRegistrationWithWrongInfo() throws Exception {
//        String baseUrl = "http://localhost";
//        mockMvc.perform(
//                        MockMvcRequestBuilders.post("/user/register")
//                                .with(csrf())
//                ).andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("http://localhost/users/register"));
////                .andExpect(view().name("redirect:users/user-login"));
//    }
}
