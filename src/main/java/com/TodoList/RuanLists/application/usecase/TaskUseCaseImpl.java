package com.TodoList.RuanLists.application.usecase;

import com.TodoList.RuanLists.domain.exception.TaskNotFoundException;
import com.TodoList.RuanLists.domain.model.tasks.TaskStatus;
import com.TodoList.RuanLists.domain.model.tasks.Tasks;
import com.TodoList.RuanLists.domain.port.in.TaskUseCase;
import com.TodoList.RuanLists.domain.port.out.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskUseCaseImpl implements TaskUseCase {

  private final TaskRepository taskRepository;

  @Override
  public Tasks create(String title, String description) {
    Tasks task = Tasks.create(title, description);
    return taskRepository.save(task);
  }

  @Override
  public Tasks findById(UUID id) {
    return taskRepository.findById(id)
      .orElseThrow(() -> new TaskNotFoundException(id));
  }

  @Override
  public List<Tasks> findAll() {
    return taskRepository.findAll();
  }

  @Override
  public List<Tasks> findByStatus(String status) {
    return taskRepository.findByStatus(status);
  }

  @Override
  public List<Tasks> search(String query) {
    return taskRepository.search(query);
  }

  @Override
  public Tasks update(UUID id, String title, String description) {
    Tasks task = findById(id);
    task.update(title, description);
    return taskRepository.save(task);
  }

  @Override
  public Tasks updateStatus(UUID id, TaskStatus status) {
    Tasks task = findById(id);
    task.updateStatus(status);
    return taskRepository.save(task);
  }

  @Override
  public void delete(UUID id) {
    findById(id);
    taskRepository.deleteById(id);
  }
} 