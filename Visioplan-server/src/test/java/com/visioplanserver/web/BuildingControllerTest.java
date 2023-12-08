package com.visioplanserver.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class BuildingControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testAddBuildingAdmin() throws Exception {
        mockMvc.perform(
                get("/building/add")
                        .with(csrf())
        ).andExpect(status().isOk()
        ).andExpect(view().name("building-add"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testAddBuildingUser() throws Exception {
        mockMvc.perform(
                get("/building/add")
                        .with(csrf())
        ).andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testBuildingsAdminViewAll() throws Exception {
        mockMvc.perform(
                        get("/building/all")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("buildings-all"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testBuildingsUserViewAll() throws Exception {
        mockMvc.perform(
                        get("/building/all")
                )
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testBuildingsAdminAddNewShouldWork() throws Exception {
        mockMvc.perform(
                        post("/building/add")
                                .param("name", "testName")
                                .param("address", "testAddress")
                                .param("city", "testCity")
                                .param("country", "testCountry")
                                .param("description", "testDescription")
                                .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/building/all"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testBuildingsAdminAddNewWrongsInfo() throws Exception {
        mockMvc.perform(
                        post("/building/add")
                                .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:add"));
    }
}



