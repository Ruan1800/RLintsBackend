package com.TodoList.RuanLists.infrastructure.persistence;

import com.TodoList.RuanLists.domain.model.Task;
import com.TodoList.RuanLists.domain.model.TaskStatus;
import com.TodoList.RuanLists.domain.port.out.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TaskRepositoryAdapter implements TaskRepository {

  private final TaskJpaRepository taskJpaRepository;

  public TaskRepositoryAdapter(TaskJpaRepository taskJpaRepository) {
    this.taskJpaRepository = taskJpaRepository;
  }

  @Override
  public Task save(Task task) {
    return taskJpaRepository.save(task);
  }

  @Override
  public Optional<Task> findById(UUID id) {
    return taskJpaRepository.findById(id);
  }

  @Override
  public List<Task> findAll() {
    return taskJpaRepository.findAll();
  }

  @Override
  public List<Task> findByStatus(TaskStatus status) {
    return taskJpaRepository.findByStatus(status);
  }

  @Override
  public List<Task> search(String query) {
    return taskJpaRepository.search(query);
  }

  @Override
  public void deleteById(UUID id) {
    taskJpaRepository.deleteById(id);
  }
}