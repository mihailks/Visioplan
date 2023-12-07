package com.visioplanserver.web;

import com.visioplanserver.model.entity.BuildingEntity;
import com.visioplanserver.testUtils.BuildingTestUtil;
import com.visioplanserver.testUtils.ItUserTestDataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ItUserTestDataUtil itUserTestDataUtil;
    @Autowired
    private BuildingTestUtil buildingTestUtil;

    @Test
    void testIndex() throws Exception {
        mockMvc.perform(
                get("/")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testHomeAnonymous() throws Exception {
        mockMvc.perform(
                get("/home")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testHomeAdmin() throws Exception {
        mockMvc.perform(
                        get("/home")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("admin-panel"));
    }

    @Test
    void testFeatures() throws Exception {
        mockMvc.perform(
                get("/features")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("features"));
    }



}
