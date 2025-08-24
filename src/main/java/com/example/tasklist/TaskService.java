package com.example.tasklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    // Create a new task
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }
    
    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
    }
    
    // Get task by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    
    // Update an existing task
    public Task updateTask(Long id, Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setPriority(taskDetails.getPriority());
            task.setDueDate(taskDetails.getDueDate());
            task.setUpdatedAt(LocalDateTime.now());
            return taskRepository.save(task);
        }
        return null;
    }
    
    // Toggle task completion status
    public Task toggleTaskCompletion(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(!task.isCompleted());
            task.setUpdatedAt(LocalDateTime.now());
            return taskRepository.save(task);
        }
        return null;
    }
    
    // Delete a task
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Get tasks by completion status
    public List<Task> getTasksByStatus(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }
    
    // Get tasks by priority
    public List<Task> getTasksByPriority(Task.Priority priority) {
        return taskRepository.findByPriority(priority);
    }
    
    // Search tasks by keyword
    public List<Task> searchTasks(String keyword) {
        return taskRepository.findByTitleOrDescriptionContaining(keyword);
    }
    
    // Get overdue tasks
    public List<Task> getOverdueTasks() {
        return taskRepository.findByDueDateBeforeAndCompletedFalse(LocalDateTime.now());
    }
    
    // Get task statistics
    public TaskStats getTaskStats() {
        long totalTasks = taskRepository.count();
        long completedTasks = taskRepository.countByCompleted(true);
        long pendingTasks = taskRepository.countByCompleted(false);
        
        return new TaskStats(totalTasks, completedTasks, pendingTasks);
    }
    
    // Inner class for task statistics
    public static class TaskStats {
        private long totalTasks;
        private long completedTasks;
        private long pendingTasks;
        
        public TaskStats(long totalTasks, long completedTasks, long pendingTasks) {
            this.totalTasks = totalTasks;
            this.completedTasks = completedTasks;
            this.pendingTasks = pendingTasks;
        }
        
        // Getters
        public long getTotalTasks() { return totalTasks; }
        public long getCompletedTasks() { return completedTasks; }
        public long getPendingTasks() { return pendingTasks; }
        
        public double getCompletionPercentage() {
            return totalTasks > 0 ? (double) completedTasks / totalTasks * 100 : 0;
        }
    }
}
