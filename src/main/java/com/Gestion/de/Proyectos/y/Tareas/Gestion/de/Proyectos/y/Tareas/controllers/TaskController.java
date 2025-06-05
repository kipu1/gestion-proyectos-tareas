package com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.controllers;

import com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.models.Task;
import com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.repository.TaskRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepo;

    @Autowired private TaskRepository repo;

    @PostMapping public ResponseEntity<Void> create(@RequestBody Task t) {
        repo.save(t);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/project/{projectId}")
    public List<Task> getByProject(@PathVariable int projectId) {
        return repo.findByProjectId(projectId);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable int id, @RequestBody Map<String, String> body) {
        repo.updateStatus(id, body.get("status"));
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Task task) {
        task.setTaskId(id);
        repo.update(task);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{taskId}/assign")
    public ResponseEntity<Void> assignEmployeeToTask(@PathVariable int taskId, @RequestBody Map<String, Integer> body) {
        Integer employeeId = body.get("employeeId");
        if (employeeId == null) {
            return ResponseEntity.badRequest().build();
        }
        taskRepo.assignEmployeeToTask(taskId, employeeId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        repo.delete(id);
        return ResponseEntity.noContent().build();
    }
}