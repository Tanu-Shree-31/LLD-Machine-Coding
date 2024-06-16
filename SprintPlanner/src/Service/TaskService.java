package Service;

import Model.Status;
import Model.Task;
import Model.Type;
import Exception.TaskException;

public interface TaskService {
    Task createTask(String title, Type type);
    void changeTaskStatus(String taskId, Status newStatus) throws TaskException;
}
