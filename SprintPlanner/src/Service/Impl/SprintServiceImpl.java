package Service.Impl;

import Model.Sprint;
import Model.Status;
import Model.Task;
import Service.SprintService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import Exception.*;

public class SprintServiceImpl implements SprintService {
    private static final int MAX_INPROGRESS_TASKS_PER_SPRINT = 2;
    private static final int MAX_TASKS_PER_SPRINT = 20;

    private Map<String, Sprint> sprints = new HashMap<>();

    @Override
    public Sprint createSprint(String name) {
        Sprint sprint = new Sprint(name);
        sprints.put(sprint.getId(), sprint);
        System.out.println("Created: "+name);
        return sprint;
    }

    @Override
    public void addTaskToSprint(String sprintId, Task task) throws SprintException {
        Sprint sprint = sprints.get(sprintId);
        if (sprint == null) {
            throw new SprintException("Sprint not found");
        }
        if (sprint.getTasks().size() >= MAX_TASKS_PER_SPRINT) {
            throw new SprintException("Sprint cannot have more than 20 tasks");
        }
        if (countInProgressTasks(sprint) >= MAX_INPROGRESS_TASKS_PER_SPRINT && task.getStatus() == Status.INPROGRESS) {
            throw new SprintException("Cannot have more than 2 tasks in progress");
        }
        sprint.getTasks().add(task);
        System.out.println("Task added to sprintId: "+sprint.getName());
    }

    @Override
    public void removeTaskFromSprint(String sprintId, String taskId) throws SprintException {
        Sprint sprint = sprints.get(sprintId);
        if (sprint == null) {
            throw new SprintException("Sprint not found");
        }
        sprint.getTasks().removeIf(task -> task.getId().equals(taskId));
    }

    @Override
    public List<Task> getTasksAssignedToUser(String username, String sprintId) throws SprintException {
        Sprint sprint = sprints.get(sprintId);
        if (sprint == null) {
            throw new SprintException("Sprint not found");
        }
        return sprint.getTasks().stream()
                .filter(task -> username.equals(task.getAssignee()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getDelayedTasks(String sprintId) throws SprintException {
        Sprint sprint = sprints.get(sprintId);
        if (sprint == null) {
            throw new SprintException("Sprint not found");
        }
        // Implement your logic for delayed tasks based on due dates or other criteria
        return sprint.getTasks().stream()
                .filter(task -> task.getStatus() != Status.DONE && task.getStatus() != Status.TODO)
                .collect(Collectors.toList());
    }

    private long countInProgressTasks(Sprint sprint) {
        return sprint.getTasks().stream()
                .filter(task -> task.getStatus() == Status.INPROGRESS)
                .count();
    }
}

