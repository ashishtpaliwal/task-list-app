package com.example.tasklist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // Find tasks by completion status
    List<Task> findByCompleted(boolean completed);
    
    // Find tasks by priority
    List<Task> findByPriority(Task.Priority priority);
    
    // Find tasks by completion status and priority
    List<Task> findByCompletedAndPriority(boolean completed, Task.Priority priority);
    
    // Find tasks containing title or description (case-insensitive search)
    @Query("SELECT t FROM Task t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Task> findByTitleOrDescriptionContaining(@Param("keyword") String keyword);
    
    // Find tasks due before a certain date
    List<Task> findByDueDateBeforeAndCompletedFalse(LocalDateTime date);
    
    // Find tasks ordered by creation date (newest first)
    List<Task> findAllByOrderByCreatedAtDesc();
    
    // Find tasks ordered by priority (HIGH, MEDIUM, LOW)
    List<Task> findAllByOrderByPriorityDesc();
    
    // Count completed and pending tasks
    long countByCompleted(boolean completed);
}
