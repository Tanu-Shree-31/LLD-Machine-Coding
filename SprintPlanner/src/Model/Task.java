package Model;

import java.util.List;
import java.util.UUID;

public class Task {
    private String id;
    private String title;
    private Type type;
    private Status status;
    private String assignee; // Username of the assigned user

    public Task(String title, Type type) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.type = type;
        this.status = Status.TODO; // Default status
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}