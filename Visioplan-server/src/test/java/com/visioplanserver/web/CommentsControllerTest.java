package com.visioplanserver.web;

import com.visioplanserver.testUtils.ItUserTestDataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CommentsControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItUserTestDataUtil itUserTestDataUtil;


    @Test
    @WithMockUser(username = "user")
    void testViewCommentsPerFile() throws Exception {
        itUserTestDataUtil.createTestFile();
        mockMvc.perform(
                get("/api/{fileId}/comments", "1")
        )
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user")
    void testPostCommentShouldWork() throws Exception {
        itUserTestDataUtil.createTestFile();
        mockMvc.perform(
                get("/api/{fileId}/comments", "1")
        )
                .andExpect(status().isOk());

    }

}
