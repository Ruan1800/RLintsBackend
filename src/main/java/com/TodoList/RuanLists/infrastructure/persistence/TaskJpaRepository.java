package com.TodoList.RuanLists.infrastructure.persistence;

import com.TodoList.RuanLists.domain.model.TaskStatus;
import com.TodoList.RuanLists.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TaskJpaRepository extends JpaRepository<Task, UUID> {
  
    List<Task> findByStatus(TaskStatus status);


    @Query("SELECT t FROM Task t WHERE " +
        "LOWER(t.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
        "LOWER(t.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Task> search(@Param("query") String query);
} 
