package com.TodoList.RuanLists.domain.port.out;

import com.TodoList.RuanLists.domain.model.tasks.Tasks;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {
  Tasks save(Tasks task);
  Optional<Tasks> findById(UUID id);
  List<Tasks> findAll();
  List<Tasks> findByStatus(String status);
  List<Tasks> search(String query);
  void deleteById(UUID id);
}
