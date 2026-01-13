package com.example.demo.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(HomeController.class) // Nur HomeController wird geladen
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeEndpoint_returnsHomeView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())       // HTTP 200
                .andExpect(view().name("home"));  // Thymeleaf-Template "home"
    }

    @Test
    void newProductEndpoint_returnsNewProductView() throws Exception {
        mockMvc.perform(get("/produkt/neu"))
                .andExpect(status().isOk())            // HTTP 200
                .andExpect(view().name("newproduct")); // Thymeleaf-Template "newproduct"
    }
}

