package com.example.tasklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    // Web endpoints for Thymeleaf templates
    @GetMapping("/")
    public String index(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        TaskService.TaskStats stats = taskService.getTaskStats();
        
        model.addAttribute("tasks", tasks);
        model.addAttribute("stats", stats);
        model.addAttribute("newTask", new Task());
        model.addAttribute("priorities", Task.Priority.values());
        
        return "index";
    }
    
    @PostMapping("/tasks")
    public String createTask(@Valid @ModelAttribute("newTask") Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Task> tasks = taskService.getAllTasks();
            TaskService.TaskStats stats = taskService.getTaskStats();
            model.addAttribute("tasks", tasks);
            model.addAttribute("stats", stats);
            model.addAttribute("priorities", Task.Priority.values());
            return "index";
        }
        
        taskService.createTask(task);
        return "redirect:/";
    }
    
    @PostMapping("/tasks/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleTaskCompletion(id);
        return "redirect:/";
    }
    
    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
    
    @GetMapping("/tasks/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            model.addAttribute("priorities", Task.Priority.values());
            return "edit-task";
        }
        return "redirect:/";
    }
    
    @PostMapping("/tasks/{id}/edit")
    public String updateTask(@PathVariable Long id, @Valid @ModelAttribute Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("task", task);
            model.addAttribute("priorities", Task.Priority.values());
            return "edit-task";
        }
        
        taskService.updateTask(id, task);
        return "redirect:/";
    }
    
    @GetMapping("/search")
    public String searchTasks(@RequestParam(required = false) String keyword, 
                             @RequestParam(required = false) String status,
                             @RequestParam(required = false) String priority,
                             Model model) {
        List<Task> tasks;
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            tasks = taskService.searchTasks(keyword);
        } else if (status != null && !status.isEmpty()) {
            boolean completed = "completed".equals(status);
            tasks = taskService.getTasksByStatus(completed);
        } else if (priority != null && !priority.isEmpty()) {
            Task.Priority taskPriority = Task.Priority.valueOf(priority.toUpperCase());
            tasks = taskService.getTasksByPriority(taskPriority);
        } else {
            tasks = taskService.getAllTasks();
        }
        
        TaskService.TaskStats stats = taskService.getTaskStats();
        
        model.addAttribute("tasks", tasks);
        model.addAttribute("stats", stats);
        model.addAttribute("newTask", new Task());
        model.addAttribute("priorities", Task.Priority.values());
        model.addAttribute("searchKeyword", keyword);
        model.addAttribute("searchStatus", status);
        model.addAttribute("searchPriority", priority);
        
        return "index";
    }
    
    // REST API endpoints
    @RestController
    @RequestMapping("/api/tasks")
    public static class TaskRestController {
        
        @Autowired
        private TaskService taskService;
        
        @GetMapping
        public List<Task> getAllTasks() {
            return taskService.getAllTasks();
        }
        
        @GetMapping("/{id}")
        public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
            Optional<Task> task = taskService.getTaskById(id);
            return task.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
        }
        
        @PostMapping
        public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
            Task createdTask = taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        }
        
        @PutMapping("/{id}")
        public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
            Task updatedTask = taskService.updateTask(id, task);
            return updatedTask != null ? ResponseEntity.ok(updatedTask) 
                                       : ResponseEntity.notFound().build();
        }
        
        @PatchMapping("/{id}/toggle")
        public ResponseEntity<Task> toggleTask(@PathVariable Long id) {
            Task toggledTask = taskService.toggleTaskCompletion(id);
            return toggledTask != null ? ResponseEntity.ok(toggledTask) 
                                       : ResponseEntity.notFound().build();
        }
        
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
            boolean deleted = taskService.deleteTask(id);
            return deleted ? ResponseEntity.noContent().build() 
                           : ResponseEntity.notFound().build();
        }
        
        @GetMapping("/stats")
        public TaskService.TaskStats getTaskStats() {
            return taskService.getTaskStats();
        }
    }
}
