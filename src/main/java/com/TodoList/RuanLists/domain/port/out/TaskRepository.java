package com.TodoList.RuanLists.domain.port.out;
import com.TodoList.RuanLists.domain.model.TaskStatus;
import com.TodoList.RuanLists.domain.model.Task;
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
