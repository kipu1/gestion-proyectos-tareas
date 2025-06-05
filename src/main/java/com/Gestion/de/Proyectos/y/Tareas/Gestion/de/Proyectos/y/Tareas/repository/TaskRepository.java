package com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.repository;

import com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {
    @Autowired private JdbcTemplate jdbc;

    public int save(Task t) {
        return jdbc.update("INSERT INTO tasks (title, description, status, project_id) VALUES (?, ?, ?, ?)",
                t.getTitle(), t.getDescription(), t.getStatus(), t.getProjectId());
    }

    public List<Task> findByProjectId(int projectId) {
        return jdbc.query("SELECT * FROM tasks WHERE project_id = ?",
                new BeanPropertyRowMapper<>(Task.class), projectId);
    }

    public int updateStatus(int taskId, String status) {
        return jdbc.update("UPDATE tasks SET status = ? WHERE task_id = ?", status, taskId);
    }

    public int delete(int taskId) {
        return jdbc.update("DELETE FROM tasks WHERE task_id = ?", taskId);
    }
    public int update(Task t) {
        return jdbc.update("UPDATE tasks SET title = ?, description = ?, status = ?, project_id = ? WHERE task_id = ?",
                t.getTitle(), t.getDescription(), t.getStatus(), t.getProjectId(), t.getTaskId());
    }
    public int assignEmployeeToTask(int taskId, int employeeId) {
        return jdbc.update("UPDATE tasks SET employee_id = ? WHERE task_id = ?", employeeId, taskId);
    }

}