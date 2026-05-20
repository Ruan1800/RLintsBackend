package com.TodoList.RuanLists.infrastructure.interfaces.rest;

import com.TodoList.RuanLists.application.dto.TaskRequestDTO;
import com.TodoList.RuanLists.application.dto.TaskResponceDTO;
import com.TodoList.RuanLists.domain.model.tasks.TaskStatus;
import com.TodoList.RuanLists.domain.port.in.TaskUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

  private final TaskUseCase taskUseCase;


  @PostMapping
  public ResponseEntity<TaskResponceDTO> create(@Valid @RequestBody TaskRequestDTO requestDTO) {
    TaskResponceDTO responceDTO = TaskResponceDTO.from(taskUseCase.create(requestDTO.getTitle(), requestDTO.getDescription()));
    return ResponseEntity.status(HttpStatus.CREATED).body(responceDTO);
  }

  @GetMapping
  public ResponseEntity<List<TaskResponceDTO>> findAll(
    @RequestParam(required = false) TaskStatus status,
    @RequestParam(required = false) String search) {
    List<TaskResponceDTO> responceDTOs;

    if (status != null) {
      responceDTOs = taskUseCase.findByStatus(status).stream()
        .map(TaskResponceDTO::from).toList();
    } else if (search != null) {
      responceDTOs = taskUseCase.search(search).stream()
        .map(TaskResponceDTO::from).toList();
    } else {
      responceDTOs = taskUseCase.findAll().stream()
        .map(TaskResponceDTO::from).toList();
    }
    return ResponseEntity.ok(responceDTOs);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskResponceDTO> findById(@RequestParam UUID id) {
    return ResponseEntity.ok(TaskResponceDTO.from(taskUseCase.findById(id)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskResponceDTO> update(@RequestParam UUID id, @Valid @RequestBody TaskRequestDTO requestDTO) {
    TaskResponceDTO responceDTO = TaskResponceDTO.from(taskUseCase.update(id, requestDTO.getTitle(), requestDTO.getDescription()));
    return ResponseEntity.ok(responceDTO);
  }

  @PatchMapping("/{id}/status")
  public ResponseEntity<TaskResponceDTO> updateStatus(@RequestParam UUID id, @RequestParam TaskStatus status) {
    TaskResponceDTO responceDTO = TaskResponceDTO.from(taskUseCase.updateStatus(id, status));
    return ResponseEntity.ok(responceDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@RequestParam UUID id) {
    taskUseCase.delete(id);
    return ResponseEntity.noContent().build();
  }
}
