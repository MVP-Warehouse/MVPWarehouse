package com.tuvarna.mvpwarehouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuvarna.mvpwarehouse.dto.LoginRequest;
import com.tuvarna.mvpwarehouse.dto.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class AuthFlowTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void unauthenticatedAccessShouldFail() throws Exception {
        mockMvc.perform(get("/api/suppliers"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void registerAndAccessProtectedEndpoint() throws Exception {
        String uniqueUsername = "testuser_" + System.currentTimeMillis();

        RegisterRequest register = new RegisterRequest();
        register.setUsername(uniqueUsername);
        register.setPassword("pass1234");
        register.setRole("AGENT");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());

        LoginRequest login = new LoginRequest();
        login.setUsername(uniqueUsername);
        login.setPassword("pass1234");

        String token = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String jwt = objectMapper.readTree(token).get("token").asText();

        mockMvc.perform(get("/api/suppliers").header("Authorization", "Bearer " + jwt))
                .andExpect(status().isOk());
    }
}

