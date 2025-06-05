package com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.models;

// Project.java
public class Project {
    private int projectId;
    private String name;
    private String description;
    private int userId;
    public Project() {
    }

    public Project(int projectId, String name, String description, int userId) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // getters y setters
}
