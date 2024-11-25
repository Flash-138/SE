import Business.Task;
import Business.Task_Manager;

public class Main {

    public static void main(String[] args) {

        // Create a list to manage tasks
        Task_Manager taskManager = new Task_Manager();

        // Create sample tasks
        Task task1 = new Task("Fix Door", "Repair the broken door on 3rd floor","Room","J8392");
        Task task2 = new Task("Fix Door", "Repair the broken door on 2cd floor","Common Area","I4563");
        Task task3 = new Task("Clean Window", "Clean the main hall windows","Kitchen","G3455");
        Task task4 = new Task("Paint Wall", "Repaint the walls in Room 101","Room","I4234");

        // Add tasks to the list
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addTask(task4);

        task1.setTask_Priority(2);
        task2.setTask_Priority(3);
        task3.setTask_Priority(5);
        task4.setTask_Priority(7);

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

        taskManager.SortRoomNum();
        taskManager.listTasks();

        taskManager.SearchTask("Fix Door","name");
        taskManager.SearchTask("1","id");

    }

}
