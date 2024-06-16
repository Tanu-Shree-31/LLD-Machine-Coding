package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Sprint {
    private String id;
    private String name;
    private List<Task> tasks;

    public Sprint(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}