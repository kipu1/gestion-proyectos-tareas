package com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.repository;

import com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Project> findAll() {
        return jdbc.query("SELECT * FROM projects", new BeanPropertyRowMapper<>(Project.class));
    }

    public Project findById(int id) {
        return jdbc.queryForObject("SELECT * FROM projects WHERE project_id = ?",
                new BeanPropertyRowMapper<>(Project.class), id);
    }

    public int save(Project p) {
        return jdbc.update("INSERT INTO projects (name, description, user_id) VALUES (?, ?, ?)",
                p.getName(), p.getDescription(), p.getUserId());
    }

    public int update(Project p) {
        return jdbc.update("UPDATE projects SET name = ?, description = ? WHERE project_id = ?",
                p.getName(), p.getDescription(), p.getProjectId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM projects WHERE project_id = ?", id);
    }

    public List<Project> searchByNameOrDescription(String q) {
        String keyword = "%" + q.toLowerCase() + "%";
        return jdbc.query(
                "SELECT * FROM projects WHERE LOWER(name) LIKE ? OR LOWER(description) LIKE ?",
                new BeanPropertyRowMapper<>(Project.class), keyword, keyword
        );
    }
    public List<Project> findByUserId(int userId) {
        return jdbc.query("SELECT * FROM projects WHERE user_id = ?",
                new BeanPropertyRowMapper<>(Project.class), userId);
    }

}
