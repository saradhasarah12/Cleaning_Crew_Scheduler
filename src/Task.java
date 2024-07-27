public class Task {
    private String description;
    private boolean isCompleted;
    private Cleaner assignedCleaner;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void completeTask() {
        this.isCompleted = true;
    }

    public Cleaner getAssignedCleaner() {
        return assignedCleaner;
    }

    public void setAssignedCleaner(Cleaner assignedCleaner) {
        this.assignedCleaner = assignedCleaner;
    }
}
