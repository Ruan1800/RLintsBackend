package com.TodoList.RuanLists.domain.port.out;

import com.TodoList.RuanLists.domain.model.tasks.TaskStatus;
import com.TodoList.RuanLists.domain.model.tasks.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {
  Task save(Task task);
  Optional<Task> findById(UUID id);
  List<Task> findAll();
  List<Task> findByStatus(TaskStatus status);
  List<Task> search(String query);
  void deleteById(UUID id);
}
