package com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.controllers;

import com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.models.Project;
import com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.models.Task;
import com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.repository.ProjectRepository;
import com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository repo;
    @Autowired
    private TaskRepository taskRepo;

    // ✅ Traer todos los proyectos de un usuario
    @GetMapping
    public List<Project> getAll(@RequestHeader("userId") int userId) {
        return repo.findByUserId(userId);
    }

    // ✅ Crear proyecto asociado al usuario
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Project project, @RequestHeader("userId") int userId) {
        project.setUserId(userId);
        repo.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Project project = repo.findById(id);
        List<Task> tasks = taskRepo.findByProjectId(id); // asegúrate que tengas inyectado taskRepo

        Map<String, Object> response = new HashMap<>();
        response.put("project", project);
        response.put("tasks", tasks);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Project project) {
        project.setProjectId(id);
        repo.update(project);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        repo.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Project> search(@RequestParam String query) {
        return repo.searchByNameOrDescription(query);
    }
}