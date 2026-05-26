package com.TodoList.RuanLists.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String title;

  private String description;

  @Enumerated(EnumType.STRING)
  private TaskStatus status;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public Task() {
  }

  public Task(
    UUID id,
    String title,
    String description,
    TaskStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public static Task create(String title, String description) {
    Task task = new Task();

    task.setTitle(title);
    task.setDescription(description);
    task.setStatus(TaskStatus.PENDING);
    task.setCreatedAt(LocalDateTime.now());
    task.setUpdatedAt(LocalDateTime.now());

    return task;
  }

  public void updateStatus(TaskStatus status) {
    this.status = status;
    this.updatedAt = LocalDateTime.now();
  }

  public void update(String title, String description) {
    this.title = title;
    this.description = description;
    this.updatedAt = LocalDateTime.now();
  }
}