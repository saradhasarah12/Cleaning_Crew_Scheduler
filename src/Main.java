import java.util.*;

public class Main {
    public static void main(String[] args) {
        Cleaner rani = new Cleaner("Rani");
        Cleaner prema = new Cleaner("Prema");

        Floor firstFloor = new Floor(1);
        firstFloor.addTask(new Task("101"));
        firstFloor.addTask(new Task("102"));
        firstFloor.addTask(new Task("103"));
        firstFloor.addTask(new Task("1A"));
        firstFloor.addTask(new Task("1B"));
        firstFloor.addTask(new Task("Washroom"));

        Floor secondFloor = new Floor(2);
        secondFloor.addTask(new Task("201"));
        secondFloor.addTask(new Task("202"));
        secondFloor.addTask(new Task("203"));
        secondFloor.addTask(new Task("204"));
        secondFloor.addTask(new Task("205"));
        secondFloor.addTask(new Task("2A"));
        secondFloor.addTask(new Task("2B"));
        secondFloor.addTask(new Task("Washroom"));
        secondFloor.addTask(new Task("2S1"));
        secondFloor.addTask(new Task("2S2"));

        Floor thirdFloor = new Floor(3);
        thirdFloor.addTask(new Task("301"));
        thirdFloor.addTask(new Task("302"));
        thirdFloor.addTask(new Task("303"));
        thirdFloor.addTask(new Task("304"));
        thirdFloor.addTask(new Task("305"));
        thirdFloor.addTask(new Task("3A"));
        thirdFloor.addTask(new Task("Washroom"));
        thirdFloor.addTask(new Task("3S1"));
        thirdFloor.addTask(new Task("3S2"));

        List<Floor> floors = Arrays.asList(firstFloor, secondFloor, thirdFloor);
        assignTasksToCleaners(floors, rani, prema);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. View Tasks");
            System.out.println("2. Mark Task as Complete");
            System.out.println("3. View Pending Tasks");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over
            switch (choice) {
                case 1:
                    System.out.println("Date: " + java.time.LocalDate.now().toString());
                    System.out.println("+-----------------+-------------------------+------------+");
                    System.out.println("| Tasks for Rani  |                         |            |");
                    System.out.println("+-----------------+-------------------------+------------+");
                    showTasksForCleaner(floors, rani);
                    System.out.println("+-----------------+-------------------------+------------+");
                    System.out.println("| Tasks for Prema |                         |            |");
                    System.out.println("+-----------------+-------------------------+------------+");
                    showTasksForCleaner(floors, prema);
                    System.out.println("+-----------------+-------------------------+------------+");
                    break;
                case 2:
                    System.out.println("Select cleaner (1 for Rani, 2 for Prema):");
                    int cleanerChoice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline left-over
                    Cleaner selectedCleaner = (cleanerChoice == 1) ? rani : prema;
                    System.out.println("Enter task numbers to complete (comma-separated):");
                    String taskNumbers = scanner.nextLine();
                    markTasksAsComplete(floors, selectedCleaner, taskNumbers);
                    break;
                case 3:
                    System.out.println("+-----------------+-------------------------+------------+");
                    System.out.println("| Pending Tasks   |                         |            |");
                    System.out.println("+-----------------+-------------------------+------------+");
                    System.out.println("| Pending Tasks for Rani  |                 |            |");
                    System.out.println("+-----------------+-------------------------+------------+");
                    showPendingTasksForCleaner(floors, rani);
                    System.out.println("+-----------------+-------------------------+------------+");
                    System.out.println("| Pending Tasks for Prema |                 |            |");
                    System.out.println("+-----------------+-------------------------+------------+");
                    showPendingTasksForCleaner(floors, prema);
                    System.out.println("+-----------------+-------------------------+------------+");
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    public static void assignTasksToCleaners(List<Floor> floors, Cleaner rani, Cleaner prema) {
        boolean assignToRani = true;
        for (Floor floor : floors) {
            for (Task task : floor.getTasks()) {
                if (assignToRani) {
                    task.setAssignedCleaner(rani);
                } else {
                    task.setAssignedCleaner(prema);
                }
                assignToRani = !assignToRani;
            }
        }
    }

    public static void showTasksForCleaner(List<Floor> floors, Cleaner cleaner) {
        System.out.println("+------+---------------+-------------------------+------------+");
        System.out.println("| No.  | Floor         | Task Description        | Status     |");
        System.out.println("+------+---------------+-------------------------+------------+");

        int taskIndex = 1;
        for (Floor floor : floors) {
            for (Task task : floor.getTasks()) {
                if (task.getAssignedCleaner().equals(cleaner)) {
                    String status = task.isCompleted() ? "Completed" : "Pending";
                    System.out.printf("| %-4d | %-13d | %-23s | %-10s |%n", taskIndex++, floor.getFloorNumber(), task.getDescription(), status);
                }
            }
        }

        System.out.println("+------+---------------+-------------------------+------------+");
    }

    public static void showPendingTasksForCleaner(List<Floor> floors, Cleaner cleaner) {
        System.out.println("+------+---------------+-------------------------+------------+");
        System.out.println("| No.  | Floor         | Task Description        | Status     |");
        System.out.println("+------+---------------+-------------------------+------------+");

        int taskIndex = 1;
        for (Floor floor : floors) {
            for (Task task : floor.getTasks()) {
                if (task.getAssignedCleaner().equals(cleaner) && !task.isCompleted()) {
                    System.out.printf("| %-4d | %-13d | %-23s | %-10s |%n", taskIndex++, floor.getFloorNumber(), task.getDescription(), "Pending");
                }
            }
        }

        System.out.println("+------+---------------+-------------------------+------------+");
    }

    public static void markTasksAsComplete(List<Floor> floors, Cleaner cleaner, String taskNumbers) {
        String[] tasks = taskNumbers.split(",");
        Set<Integer> taskSet = new HashSet<>();
        for (String task : tasks) {
            try {
                taskSet.add(Integer.parseInt(task.trim()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number format: " + task.trim());
                return;
            }
        }

        int taskIndex = 1;
        for (Floor floor : floors) {
            for (Task task : floor.getTasks()) {
                if (task.getAssignedCleaner().equals(cleaner)) {
                    if (taskSet.contains(taskIndex)) {
                        task.completeTask();
                        System.out.println("Task " + taskIndex + " completed.");
                    }
                    taskIndex++;
                }
            }
        }
    }
}

