package com.visioplanserver.web;

import com.visioplanserver.model.entity.UserEntity;
import com.visioplanserver.testUtils.ItUserTestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTestIT {

    private static final String TEST_USER_OWNER_NAME = "userOwner";
    private static final String TEST_USER_NOT_OWNER_NAME = "userNotOwner";
    private static final String TEST_ADMIN_NAME = "admin";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ItUserTestDataUtil itUserTestDataUtil;

    @BeforeEach
    void setUp() {
        itUserTestDataUtil.cleanUp();
    }
    @AfterEach
    void tearDown() {
        itUserTestDataUtil.cleanUp();
    }

    @Test
    void testAnonymousUserProfile() throws Exception {
        itUserTestDataUtil.createTestUser(TEST_USER_OWNER_NAME);
        mockMvc.perform(
                get("/users/profile")
                        .with(csrf())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser(username = TEST_ADMIN_NAME, roles = {"ADMIN", "USER"})
    void testAdminUserProfileDelete() throws Exception {
        itUserTestDataUtil.createTestAdmin(TEST_ADMIN_NAME);
        UserEntity testUser = itUserTestDataUtil.createTestUser(TEST_USER_OWNER_NAME);

        mockMvc.perform(
                        delete("/user/delete/{id}", testUser.getId())
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/all"));
    }

    @Test
    @WithMockUser(username = TEST_ADMIN_NAME, roles = {"ADMIN", "USER"})
    void testUserPromote() throws Exception {
        itUserTestDataUtil.createTestAdmin(TEST_ADMIN_NAME);
        UserEntity testUser = itUserTestDataUtil.createTestUser(TEST_USER_OWNER_NAME);
        mockMvc.perform(
                        post("/user/promote/{id}", testUser.getId())
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/all/0"));
    }

    @Test
    @WithMockUser(username = TEST_ADMIN_NAME, roles = {"ADMIN", "USER"})
    void testUserDemote() throws Exception {
        itUserTestDataUtil.createTestAdmin(TEST_ADMIN_NAME);
        UserEntity testUser = itUserTestDataUtil.createTestUser(TEST_USER_OWNER_NAME);
        mockMvc.perform(
                        post("/user/demote/{id}", testUser.getId())
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/all/0"));
    }

    @Test
    void testGetAllPagesAnonymous() throws Exception {
        mockMvc.perform(
                        get("/user/all/1")
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser(username = TEST_USER_NOT_OWNER_NAME)
    void testGetAllPagesUser() throws Exception {

        mockMvc.perform(
                        get("/user/all/1")
                                .with(csrf())
                )
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = TEST_USER_OWNER_NAME)
    void testEditProfile() throws Exception {
        UserEntity testUser = itUserTestDataUtil.createTestUser(TEST_USER_OWNER_NAME);
        mockMvc.perform(
                        post("/user/profile/edit/{id}", testUser.getId())
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/profile/edit"));
    }











}
