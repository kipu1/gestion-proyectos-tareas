package com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.controllers;


import com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.models.User;
import com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (repo.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Usuario ya existe");
        }
        repo.save(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User dbUser = repo.findByUsername(user.getUsername());
            if (dbUser.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok().body(new LoginResponse(dbUser.getUserId(), dbUser.getUsername()));
            } else {
                return ResponseEntity.status(401).body("Contraseña incorrecta");
            }
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }
    }

    static class LoginResponse {
        public int userId;
        public String username;

        public LoginResponse(int userId, String username) {
            this.userId = userId;
            this.username = username;
        }

        public int getUserId() {
            return userId;
        }

        public String getUsername() {
            return username;
        }
    }
}
