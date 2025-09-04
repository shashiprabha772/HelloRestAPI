package com.example.HelloRestAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureTestMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureTestMvc
@ActiveProfiles("test")
@Transactional
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSimpleHello() throws Exception {
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from Hello Rest API"));
    }

    @Test
    public void testGreetUser() throws Exception {
        mockMvc.perform(get("/api/hello/greet/John"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello John! Welcome to Hello Rest API!"));
    }

    @Test
    public void testGetAPIStatus() throws Exception {
        mockMvc.perform(get("/api/hello/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.service").value("Hello API"))
                .andExpect(jsonPath("$.version").value("1.0.0"));
    }

    @Test
    public void testCreateHelloMessage() throws Exception {
        HelloRequestDTO requestDTO = new HelloRequestDTO();
        requestDTO.setMessage("Test message");
        requestDTO.setSender("Test User");

        mockMvc.perform(post("/api/hello/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Test message"))
                .andExpect(jsonPath("$.sender").value("Test User"))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testGetAllMessages() throws Exception {
        mockMvc.perform(get("/api/hello/message"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
