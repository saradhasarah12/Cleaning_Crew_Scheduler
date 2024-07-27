import java.util.ArrayList;
import java.util.List;

public class Floor {
    private int floorNumber;
    private List<Task> tasks;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.tasks = new ArrayList<>();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void showTasks() {
        System.out.printf("%-15s%-25s%-15s\n", "Floor", "Task Description", "Assigned To");
        for (Task task : tasks) {
            String status = task.isCompleted() ? "Completed" : "Pending";
            System.out.printf("%-15d%-25s%-15s\n", floorNumber, task.getDescription(), task.getAssignedCleaner().getName() + " (" + status + ")");
        }
    }
}
