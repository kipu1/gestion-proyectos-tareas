package com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.repository;



import com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public void save(User user) {
        jdbc.update("INSERT INTO users (username, password) VALUES (?, ?)",
                user.getUsername(), user.getPassword());
    }

    public User findByUsername(String username) {
        return jdbc.queryForObject("SELECT * FROM users WHERE username = ?",
                new BeanPropertyRowMapper<>(User.class), username);
    }

    public boolean existsByUsername(String username) {
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM users WHERE username = ?",
                Integer.class, username);
        return count != null && count > 0;
    }
}
