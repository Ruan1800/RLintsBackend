package com.TodoList.RuanLists.domain.model.tasks;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Tasks {

      @Id
      @GeneratedValue(strategy = GenerationType.UUID)
      private UUID id;
      private String title;
      private String description;
      
      @Enumerated(EnumType.STRING)
      private TaskStatus status;
      
      private LocalDateTime createdAt;
      private LocalDateTime updatedAt;
      
      public static Tasks create(String title, String description) {
            return Tasks.builder()
                    .id(UUID.randomUUID())
                    .title(title)
                    .description(description)
                    .status(TaskStatus.PENDING)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
      }
      
      public void updateStatus(TaskStatus newStatus) {
            this.status = newStatus;
            this.updatedAt = LocalDateTime.now();
      }
      
      public void update(String title, String description) {
            this.title = title;
            this.description = description;
            this.updatedAt = LocalDateTime.now();
      }
      
}
