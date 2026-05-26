package com.TodoList.RuanLists.application.usecase;

import com.TodoList.RuanLists.domain.exception.TaskNotFoundException;
import com.TodoList.RuanLists.domain.model.Task;
import com.TodoList.RuanLists.domain.model.TaskStatus;
import com.TodoList.RuanLists.domain.port.out.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskUseCaseImplTest {

  @Mock
  private TaskRepository taskRepository;

  @InjectMocks
  private TaskUseCaseImpl useCase;

  private Task task;

  @BeforeEach
  void setUp() {
    task = Task.create("Test Task", "Test Description");
  }

  @Test
  void shouldCreateTask() {
    when(taskRepository.save(any(Task.class))).thenReturn(task);

    Task result = useCase.create("Test Task", "Test Description");

    assertThat(result).isNotNull();
    assertThat(result.getTitle()).isEqualTo("Test Task");
    assertThat(result.getStatus()).isEqualTo(TaskStatus.PENDING);
    verify(taskRepository, times(1)).save(any(Task.class));
  }

  @Test
  void shouldFindTaskById() {
    when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

    Task result = useCase.findById(task.getId());

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(task.getId());
  }

  @Test
  void shouldThrowWhenTaskNotFound() {
    UUID id = UUID.randomUUID();
    when(taskRepository.findById(id)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.findById(id))
      .isInstanceOf(TaskNotFoundException.class);
  }

  @Test
  void shouldReturnAllTasks() {
    when(taskRepository.findAll()).thenReturn(List.of(task));

    List<Task> result = useCase.findAll();

    assertThat(result).hasSize(1);
  }

  @Test
  void shouldUpdateTaskStatus() {
    when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
    when(taskRepository.save(any(Task.class))).thenReturn(task);

    Task result = useCase.updateStatus(task.getId(), TaskStatus.IN_PROGRESS);

    assertThat(result.getStatus()).isEqualTo(TaskStatus.IN_PROGRESS);
  }

  @Test
  void shouldDeleteTask() {
    when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

    useCase.delete(task.getId());

    verify(taskRepository, times(1)).deleteById(task.getId());
  }
}