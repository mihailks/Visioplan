package com.visioplanserver.web;

import com.visioplanserver.testUtils.ItUserTestDataUtil;
import com.visioplanserver.web.interceptorTest.PlatformDetector;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItUserTestDataUtil itUserTestDataUtil;

    @Autowired
    PlatformDetector platformDetector;
    @Autowired
    HttpServletRequest request;


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testViewAllFiles() throws Exception {
        itUserTestDataUtil.createTestFile();
        itUserTestDataUtil.createTestAdmin("admin");

        String userAgent = "User-Agent";

        mockMvc.perform(
                        get("/file/all/{pageNumber}", 2)
                                .header("User-Agent", userAgent)
                )
                .andExpect(status().isOk())
                .andExpect(view().name("files-all"));
    }

    @Test
    @WithMockUser(username = "user")
    void testFilePerBuilding() throws Exception {
        itUserTestDataUtil.createTestFile();

        String userAgent = "User-Agent";

        mockMvc.perform(
                        get("/file/{buildingId}", 1)
                                .header("User-Agent", userAgent)
                )
                .andExpect(status().isOk())
                .andExpect(view().name("files-all-per-building"));
    }

    @Test
    void AddNewFileGetNoUser() throws Exception {
        String userAgent = "User-Agent";

        mockMvc.perform(
                        get("/file/add")
                                .header("User-Agent", userAgent)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser(username = "user")
    void AddNewFileGet() throws Exception {
        String userAgent = "User-Agent";

        mockMvc.perform(
                        get("/file/add")
                                .header("User-Agent", userAgent)
                )
                .andExpect(status().isOk())
                .andExpect(view().name("file-upload"));
    }

    @Test
    @WithMockUser(username = "user")
    void AddNewFilePostInvalidInput() throws Exception {
        String userAgent = "User-Agent";

        mockMvc.perform(
                        post("/file/add")
                                .header("User-Agent", userAgent)
                                .param("name", "test")
                                .param("description", "test")
                                .param("buildingId", "1")
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:add"));
    }

    @Test
    @WithMockUser(username = "user")
    void AddNewFilePostShouldWork() throws Exception {
        String userAgent = "User-Agent";

        mockMvc.perform(
                        post("/file/add")
                                .header("User-Agent", userAgent)
                                .param("name", "test")
                                .param("floor", "1")
                                .param("part", "test")
                                .param("extension", "DWG")
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:add"));
    }




}


