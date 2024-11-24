import Business.Task;
import Business.Task_Manager;

public class Main {

    public static void main(String[] args) {

        // Create a list to manage tasks
        Task_Manager taskManager = new Task_Manager();

        // Create sample tasks
        Task task1 = new Task("Fix Door", "Repair the broken door on 3rd floor","Room");
        Task task2 = new Task("Clean Window", "Clean the main hall windows","Kitchen");
        Task task3 = new Task("Paint Wall", "Repaint the walls in Room 101","Room");

        // Add tasks to the list
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        task1.setTask_Priority(2);
        task2.setTask_Priority(1);
        task3.setTask_Priority(3);
        

        // Print all tasks
        taskManager.listTasks();

        taskManager.SortTaskPriority();
        taskManager.listTasks();

        taskManager.SortTaskID();
        taskManager.listTasks();

        taskManager.SortTaskName();
        taskManager.listTasks();

        taskManager.SortTaskViaTime();
        taskManager.listTasks();

        taskManager.SortTaskCategory();
        taskManager.listTasks();

        taskManager.SearchTask("Fix Door","name");
        taskManager.SearchTask("1","id");

    }

}
