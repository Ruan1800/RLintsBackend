package com.TodoList.RuanLists.domain.port.in;

import com.TodoList.RuanLists.domain.model.TaskStatus;
import com.TodoList.RuanLists.domain.model.Task;
import java.util.List;
import java.util.UUID;
public interface TaskUseCase {

  Task create(String title, String description);
  Task findById(UUID id);
  List<Task> findAll();
  List<Task> findByStatus(TaskStatus status);
  List<Task> search(String query);
  Task update(UUID id, String title, String description);
  Task updateStatus(UUID id, TaskStatus status);
  void delete(UUID id);
}
