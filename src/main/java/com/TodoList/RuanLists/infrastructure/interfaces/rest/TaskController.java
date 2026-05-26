package com.TodoList.RuanLists.infrastructure.interfaces.rest;

import com.TodoList.RuanLists.application.dto.TaskRequestDTO;
import com.TodoList.RuanLists.application.dto.TaskResponseDTO;
import com.TodoList.RuanLists.domain.model.TaskStatus;
import com.TodoList.RuanLists.domain.port.in.TaskUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskUseCase taskUseCase;

  public TaskController(TaskUseCase taskUseCase) {
    this.taskUseCase = taskUseCase;
  }

  @PostMapping
  public ResponseEntity<TaskResponseDTO> create(
    @Valid @RequestBody TaskRequestDTO requestDTO
  ) {

    TaskResponseDTO responseDTO = TaskResponseDTO.from(
      taskUseCase.create(
        requestDTO.getTitle(),
        requestDTO.getDescription()
      )
    );

    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(responseDTO);
  }

  @GetMapping
  public ResponseEntity<List<TaskResponseDTO>> findAll(
    @RequestParam(required = false) TaskStatus status,
    @RequestParam(required = false) String search
  ) {

    List<TaskResponseDTO> responseDTOs;

    if (status != null) {

      responseDTOs = taskUseCase.findByStatus(status)
        .stream()
        .map(TaskResponseDTO::from)
        .toList();

    } else if (search != null) {

      responseDTOs = taskUseCase.search(search)
        .stream()
        .map(TaskResponseDTO::from)
        .toList();

    } else {

      responseDTOs = taskUseCase.findAll()
        .stream()
        .map(TaskResponseDTO::from)
        .toList();
    }

    return ResponseEntity.ok(responseDTOs);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskResponseDTO> findById(
    @PathVariable UUID id
  ) {

    return ResponseEntity.ok(
      TaskResponseDTO.from(taskUseCase.findById(id))
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskResponseDTO> update(
    @PathVariable UUID id,
    @Valid @RequestBody TaskRequestDTO requestDTO
  ) {

    TaskResponseDTO responseDTO = TaskResponseDTO.from(
      taskUseCase.update(
        id,
        requestDTO.getTitle(),
        requestDTO.getDescription()
      )
    );

    return ResponseEntity.ok(responseDTO);
  }

  @PatchMapping("/{id}/status")
  public ResponseEntity<TaskResponseDTO> updateStatus(
    @PathVariable UUID id,
    @RequestParam TaskStatus status
  ) {

    TaskResponseDTO responseDTO = TaskResponseDTO.from(
      taskUseCase.updateStatus(id, status)
    );

    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {

    taskUseCase.delete(id);

    return ResponseEntity.noContent().build();
  }
}