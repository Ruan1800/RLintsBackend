package com.TodoList.RuanLists.interfaces.rest;

import com.TodoList.RuanLists.application.dto.TaskRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test")
class TaskControllerIntegrationTest {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldCreateTask() throws Exception {
    TaskRequestDTO request = new TaskRequestDTO("Integration Test Task", "Description");

    mockMvc.perform(post("/tasks")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.title").value("Integration Test Task"))
      .andExpect(jsonPath("$.status").value("PENDING"));
  }

  @Test
  void shouldReturnAllTasks() throws Exception {
    mockMvc.perform(get("/tasks"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$").isArray());
  }

  @Test
  void shouldReturn404WhenTaskNotFound() throws Exception {
    mockMvc.perform(get("/tasks/00000000-0000-0000-0000-000000000000"))
      .andExpect(status().isNotFound());
  }

  @Test
  void shouldReturn400WhenTitleIsBlank() throws Exception {
    TaskRequestDTO request = new TaskRequestDTO("", "Description");

    mockMvc.perform(post("/tasks")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
      .andExpect(status().isBadRequest());
  }
}