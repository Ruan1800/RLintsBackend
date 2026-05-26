package com.TodoList.RuanLists.application.usecase;

import com.TodoList.RuanLists.domain.exception.TaskNotFoundException;
import com.TodoList.RuanLists.domain.model.Task;
import com.TodoList.RuanLists.domain.model.TaskStatus;
import com.TodoList.RuanLists.domain.port.in.TaskUseCase;
import com.TodoList.RuanLists.domain.port.out.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskUseCaseImpl implements TaskUseCase {

  private final TaskRepository taskRepository;

  public TaskUseCaseImpl(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public Task create(String title, String description) {
    Task task = Task.create(title, description);
    return taskRepository.save(task);
  }

  @Override
  public Task findById(UUID id) {
    return taskRepository.findById(id)
      .orElseThrow(() -> new TaskNotFoundException(id));
  }

  @Override
  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  @Override
  public List<Task> findByStatus(TaskStatus status) {
    return taskRepository.findByStatus(status);
  }

  @Override
  public List<Task> search(String query) {
    return taskRepository.search(query);
  }

  @Override
  public Task update(UUID id, String title, String description) {
    Task task = findById(id);
    task.update(title, description);
    return taskRepository.save(task);
  }

  @Override
  public Task updateStatus(UUID id, TaskStatus status) {
    Task task = findById(id);
    task.updateStatus(status);
    return taskRepository.save(task);
  }

  @Override
  public void delete(UUID id) {
    findById(id);
    taskRepository.deleteById(id);
  }
}