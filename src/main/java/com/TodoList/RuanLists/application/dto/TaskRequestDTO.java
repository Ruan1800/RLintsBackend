package com.TodoList.RuanLists.application.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {

  @NotBlank(message = "Title is required")
  private String title;
  private String description;
}
