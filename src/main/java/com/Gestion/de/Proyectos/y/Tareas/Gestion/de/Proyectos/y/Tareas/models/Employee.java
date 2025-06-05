package com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.models;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private int userId;
    private Integer taskId; // puede ser null si no se asignó tarea

    public Employee() {}

    public Employee(int employeeId, String firstName, String lastName, String email, int userId, Integer taskId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userId = userId;
        this.taskId = taskId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
