package Service;

import Model.Sprint;
import Model.Task;

import java.util.List;
import Exception.SprintException;

public interface SprintService {
    Sprint createSprint(String name);
    void addTaskToSprint(String sprintId, Task task) throws SprintException;
    void removeTaskFromSprint(String sprintId, String taskId) throws SprintException;
    List<Task> getTasksAssignedToUser(String username, String sprintId) throws SprintException;
    List<Task> getDelayedTasks(String sprintId) throws SprintException;
}
