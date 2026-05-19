package com.TodoList.RuanLists.domain.port.in;

import com.TodoList.RuanLists.domain.model.tasks.Tasks;

import java.util.List;
import java.util.UUID;

public interface TaskUseCase {

  Tasks create(String title, String description);
  Tasks findById(UUID id);
  List<Tasks> findAll();
  List<Tasks> findByStatus(String status);
  List<Tasks> search(String query);
  Tasks update(UUID id, String title, String description);
  Tasks updateStatus(UUID id, String status);
  void delete(UUID id);
  
}
