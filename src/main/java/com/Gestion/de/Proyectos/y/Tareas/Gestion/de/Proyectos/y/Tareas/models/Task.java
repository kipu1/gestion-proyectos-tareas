package com.Gestion.de.Proyectos.y.Tareas.Gestion.de.Proyectos.y.Tareas.models;

public class Task {
    private int taskId;
    private String title;
    private String description;
    private String status;
    private int projectId;

    public Task() {
    }

    public Task(int taskId, String title, String description, String status, int projectId) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.projectId = projectId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}