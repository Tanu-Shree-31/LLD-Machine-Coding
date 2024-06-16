package Driver;

import Model.Sprint;
import Model.Status;
import Model.Task;
import Model.Type;
import Service.Impl.SprintServiceImpl;
import Service.Impl.TaskServiceImpl;
import Service.SprintService;
import Service.TaskService;
import Exception.*;

import java.util.List;

public class SprintPlannerApp {
    public static void main(String[] args) throws SprintException {
        SprintService sprintService = new SprintServiceImpl();
        TaskService taskService = new TaskServiceImpl();

        Sprint sprint1 = sprintService.createSprint("Sprint 1");

        System.out.println();
        Task task1 = taskService.createTask("Implement login feature", Type.FEATURE);
        Task task2 = taskService.createTask("Fix bug in profile page", Type.BUG);
        Task task3 = taskService.createTask("Create new button", Type.FEATURE);

        task1.setAssignee("tanu");
        task2.setAssignee("kavya");
        task3.setAssignee("tanu");

        try {
            sprintService.addTaskToSprint(sprint1.getId(), task1);
            sprintService.addTaskToSprint(sprint1.getId(), task2);
            sprintService.addTaskToSprint(sprint1.getId(), task3);

            System.out.println();
            taskService.changeTaskStatus(task1.getId(), Status.INPROGRESS);
            //Exception case where we cannot change TO_DO->DONE
            //taskService.changeTaskStatus(task2.getId(), Status.DONE);

            List<Task> userTasks = sprintService.getTasksAssignedToUser("alice", sprint1.getId());
            System.out.println("\nTasks assigned to Tanu:");
            for (Task task : userTasks) {
                System.out.println(task.getTitle() + " - " + task.getStatus());
            }

            List<Task> delayedTasks = sprintService.getDelayedTasks(sprint1.getId());
            System.out.println("\nDelayed tasks:");
            for (Task task : delayedTasks) {
                System.out.println(task.getTitle() + " - " + task.getStatus());
            }

            // Remove task2 from the sprint
            sprintService.removeTaskFromSprint(sprint1.getId(), task2.getId());

            System.out.println("\nTasks in the sprint after removal:");
            List<Task> tasksInSprint = sprint1.getTasks();
            for (Task task : tasksInSprint) {
                System.out.println(task.getTitle() + " - " + task.getStatus());
            }
        } catch (SprintException | TaskException e) {
            e.printStackTrace();
        }
    }
}
