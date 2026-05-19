package com.TodoList.RuanLists.application.dto;

import com.TodoList.RuanLists.domain.model.tasks.TaskStatus;
import com.TodoList.RuanLists.domain.model.tasks.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class TaskResponceDTO {

  private UUID id;
  private String title;
  private String description;
  private TaskStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  
    public static TaskResponceDTO from(Task task) {
        return new TaskResponceDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

}
