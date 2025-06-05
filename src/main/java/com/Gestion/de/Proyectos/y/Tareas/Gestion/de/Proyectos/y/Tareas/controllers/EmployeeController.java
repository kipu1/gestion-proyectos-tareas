package com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.controllers;

import com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.models.Employee;
import com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repo;

    @GetMapping
    public List<Employee> getAll(@RequestHeader("userId") int userId) {
        return repo.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Employee e, @RequestHeader("userId") int userId) {
        e.setUserId(userId);

        if (repo.existsByEmailAndUserId(e.getEmail(), userId)) {
            return ResponseEntity.status(409).body("Email ya está registrado para este usuario.");
        }

        repo.save(e);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Employee e) {
        e.setEmployeeId(id);
        repo.update(e);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        repo.delete(id);
        return ResponseEntity.noContent().build();
    }

}
