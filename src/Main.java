import Base.*;
import Business.*;

public class Main {
    public static void main(String[] args) {
        // Create a Login system
        Login loginSystem = new Login();

        // Create Staff and Resident users
        loginSystem.createUser("John Doe", "john_doe", "password123", "adminKey");  // Staff
        loginSystem.createUser("Alice Resident", "alice_resident", "password456", "I1234");  // Resident (I---- format)
        loginSystem.createUser("Bob Resident", "bob_resident", "password789", "J5678");  // Resident (J---- format)
        loginSystem.createUser("Charlie Resident", "charlie_resident", "password1011", "G4321");  // Resident (G---- format)
        loginSystem.createUser("Diana Resident", "diana_resident", "password1213", "H8765");  // Resident (H---- format)

        // Try logging in with correct credentials
        loginSystem.login("john_doe", "password123");  // Staff login
        loginSystem.login("alice_resident", "password456");  // Resident login

        // Show all users
        System.out.println("All users in the system:");
        loginSystem.showAllUsers();

        // Delete a user
        loginSystem.deleteUser("alice_resident");  // Delete Resident

        // Show all users after deletion
        System.out.println("All users in the system after deletion:");
        loginSystem.showAllUsers();
    

        // Create a list to manage tasks
        Task_Manager taskManager = new Task_Manager();

        // Create sample tasks
        Task task1 = new Task("Fix Door", "Repair the broken door on 3rd floor","Room","J8392");
        Task task2 = new Task("Fix Door", "Repair the broken door on 2cd floor","Room","I4563");
        Task task3 = new Task("Clean Window", "Clean the main hall windows","Kitchen","G3455");
        Task task4 = new Task("Paint Wall", "Repaint the walls in Room 101","Room","I4563");

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
        //taskManager.listTasks();

        //taskManager.SortTaskPriority();
        //taskManager.listTasks();

        //taskManager.SortTaskID();
        //taskManager.listTasks();

        //taskManager.SortTaskName();
        //taskManager.listTasks();

        //taskManager.SortTaskViaTime();
        //taskManager.listTasks();

        //cleartaskManager.SortRoomNum();
        //taskManager.listTasks();

       //taskManager.SearchTask("Room","Category");
       //taskManager.SearchTask("1","Task id");
        
        System.out.println(task1.TaskComplete());

    }

}
