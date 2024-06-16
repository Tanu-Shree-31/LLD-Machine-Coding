package Service.Impl;

import Model.Status;
import Model.Task;
import Model.Type;
import Service.TaskService;
import Exception.TaskException;

import java.util.HashMap;
import java.util.Map;

public class TaskServiceImpl implements TaskService {
    private Map<String, Task> tasks = new HashMap<>();

    @Override
    public Task createTask(String title, Type type) {
        Task task = new Task(title, type);
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public void changeTaskStatus(String taskId, Status newStatus) throws TaskException {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new TaskException("Task not found");
        }
        Status currentStatus = task.getStatus();
        System.out.println("Current task status is: "+currentStatus);

        // Check if the status change is valid
        switch (newStatus) {
            case TODO:
                if (currentStatus == Status.INPROGRESS || currentStatus == Status.DONE) {
                    throw new TaskException("Cannot change to TODO from INPROGRESS or DONE status");
                }
                task.setStatus(newStatus);
                break;
            case INPROGRESS:
                if (currentStatus == Status.TODO) {
                    task.setStatus(newStatus);
                } else {
                    throw new TaskException("Cannot change to INPROGRESS from non-TODO status");
                }
                break;
            case DONE:
                if (currentStatus == Status.INPROGRESS) {
                    task.setStatus(newStatus);
                } else {
                    throw new TaskException("Cannot change to DONE from non-INPROGRESS status");
                }
                break;
            default:
                throw new TaskException("Unsupported status change");
        }
    }
}

