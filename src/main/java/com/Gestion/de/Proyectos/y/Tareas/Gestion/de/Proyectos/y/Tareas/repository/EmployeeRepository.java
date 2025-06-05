package com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.repository;

import com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Employee> findByUserId(int userId) {
        return jdbc.query("SELECT * FROM employees WHERE user_id = ?",
                new BeanPropertyRowMapper<>(Employee.class), userId);
    }

    public Employee findById(int id) {
        return jdbc.queryForObject("SELECT * FROM employees WHERE employee_id = ?",
                new BeanPropertyRowMapper<>(Employee.class), id);
    }

    public void save(Employee e) {
        jdbc.update("INSERT INTO employees (first_name, last_name, email, user_id) VALUES (?, ?, ?, ?)",
                e.getFirstName(), e.getLastName(), e.getEmail(), e.getUserId());
    }

    public void update(Employee e) {
        jdbc.update("UPDATE employees SET first_name = ?, last_name = ?, email = ? WHERE employee_id = ?",
                e.getFirstName(), e.getLastName(), e.getEmail(), e.getEmployeeId());
    }

    public void delete(int id) {
        jdbc.update("DELETE FROM employees WHERE employee_id = ?", id);
    }
}
