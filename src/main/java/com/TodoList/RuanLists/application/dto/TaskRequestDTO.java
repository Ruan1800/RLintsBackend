package com.TodoList.RuanLists.application.dto;

import jakarta.validation.constraints.NotBlank;

public class TaskRequestDTO {

  @NotBlank(message = "Title is required")
  private String title;

  private String description;

  public TaskRequestDTO() {
  }

  public TaskRequestDTO(String title, String description) {
    this.title = title;
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}