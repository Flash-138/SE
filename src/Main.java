import java.util.ArrayList;

import Application.Notification.EmailData;
import Application.Notification.Notification;
import Base.*;
import Base.CLIManager.CLIOption;
import Business.*;
import Data.DatabaseManager;

public class Main {
    enum UITab {
        Notifications,
        Tasks,
        SpecificTask,
        CreateTask,
        Resident,
    }

    static UITab currentTab = null;
    static Task currentTask = null;
    static Task_Manager taskManager;
    static DatabaseManager database;
    private static Person user;

    public static void main(String[] args) throws Exception {
        database = new DatabaseManager(); // Load the database

        taskManager = new Task_Manager();
        database.getTasks().forEach(task -> taskManager.addTask(task));

        var ui = new CLIManager();
        var login = new Login(database);

        user = login.TryLogin(ui); // Login the user

        if (user == null) {
            ui.DisplayMessage("Invalid login credentials. Goodbye!");
            return;
        }
        currentTab = UITab.Notifications;

        ui.DisplayMessage("Welcome, " + user.getName() + "!");

        if (user instanceof Staff) {
            openStaffUI(ui);
        } else {
            openResidentUI(ui);
        }
    }

    private static void openResidentUI(CLIManager ui) {
        while (true) {
            ui.DisplayMessage("Current tab: " + currentTab);
            switch (currentTab) {
                case SpecificTask:
                    ui.CreateOptionMenu("Task: " + currentTask.getTask_name(), new CLIOption[] {
                            new CLIOption("Recant Task", () -> {
                                // Recant the task
                                taskManager.removeTask(currentTask);
                                // Update the database
                                database.reloadTasks(taskManager.GetTasks());

                                var emailData = new EmailData();
                                emailData.setRecipient(currentTask.reporter);
                                emailData.setSubject("Task recanted: " + currentTask.getTask_name());
                                emailData.setText("The task " + currentTask.getTask_name() + " has been recanted.");

                                var notification = new Notification(emailData);
                                notification.send();

                                database.addItem(notification);

                                ui.DisplayMessage("Task recanted");

                            }),

                            new CLIOption("Go back", () -> currentTab = UITab.Tasks),
                    });
                    break;
                default:
                    ui.CreateOptionMenu("", new CLIOption[] {
                            new CLIOption("Notifications", () -> {
                                // Print all the notifications that are for the user
                                var notifications = database.getNotifications();
                                for (Notification notification : notifications) {
                                    if (notification.getRecipient().equals(user)) {
                                        ui.DisplayMessage(notification.toString());
                                    }
                                }
                            }),
                            new CLIOption("Tasks", () -> {
                                // Print all the tasks and allow the user to select a task
                                var tasks = taskManager.GetTasks();
                                ArrayList<Task> userTasks = new ArrayList<>();
                                for (Task task : tasks) {
                                    if (task.reporter.equals(user)) {
                                        userTasks.add(task);
                                    }
                                }
                                CLIOption[] taskOptions = new CLIOption[userTasks.size() + 1];
                                for (int i = 0; i < userTasks.size(); i++) {
                                    Task task = userTasks.get(i);
                                    taskOptions[i] = new CLIOption(task.getTask_name(), () -> {
                                        currentTab = UITab.SpecificTask;
                                        currentTask = task;
                                    });
                                }
                                taskOptions[userTasks.size()] = new CLIOption("Go back",
                                        () -> currentTab = UITab.Notifications);
                                ui.CreateOptionMenu("Tasks", taskOptions);

                            }),
                            new CLIOption("Change Password", () -> {
                                ui.DisplayMessage("Enter your current password: ");
                                String currentPassword = ui.GetInputString();

                                if (user.getPassword().equals(currentPassword)) {
                                    ui.DisplayMessage("Invalid password");

                                } else {

                                    ui.DisplayMessage("Enter your new password: ");
                                    String newPassword = ui.GetInputString();

                                    user.setPassword(newPassword);
                                    ui.DisplayMessage("Password changed successfully");
                                }
                            }),
                            new CLIOption("Create Task", () -> {
                                ui.DisplayMessage("Enter the task name: ");
                                String taskName = ui.GetInputString();

                                ui.DisplayMessage("Enter the task description: ");
                                String taskDescription = ui.GetInputString();

                                ui.DisplayMessage("Enter the task category: ");
                                String taskCategory = ui.GetInputString();

                                ui.DisplayMessage("Enter the room number: ");
                                String roomNumber = ui.GetInputString();

                                var task = new Task(taskName, taskDescription, taskCategory, roomNumber);
                                // task.setTask_Priority(taskPriority);

                                task.reporter = (Resident) user;
                                taskManager.addTask(task);

                                EmailData emailData = new EmailData();
                                emailData.setRecipient(user);
                                emailData.setSubject("Task created: " + task.getTask_name());
                                emailData.setText("The task " + task.getTask_name() + " has been created.");

                                Notification notification = new Notification(emailData);
                                notification.send();

                                database.addItem(notification);
                                database.addItem(task);

                                ui.DisplayMessage("Task created successfully");
                            }),
                            new CLIOption("Logout", () -> {
                                ui.DisplayMessage("Logging out...");
                                return;
                            }),
                    });
                    break;
            }
        }
    }

    public static void openStaffUI(CLIManager ui) {
        while (true) {
            ui.DisplayMessage("Current tab: " + currentTab);
            switch (currentTab) {

                case SpecificTask:
                    ui.CreateOptionMenu("Task: " + currentTask.getTask_name(), new CLIOption[] {
                            new CLIOption("Mark as complete", () -> {
                                currentTask.TaskComplete();

                                var emailData = new EmailData();
                                emailData.setRecipient(currentTask.reporter);
                                emailData.setSubject("Task completed: " + currentTask.getTask_name());
                                emailData.setText("The task " + currentTask.getTask_name() + " has been completed.");

                                var notification = new Notification(emailData);
                                notification.send();

                                database.addItem(notification);

                                ui.DisplayMessage("Task marked as complete");

                            }),
                            new CLIOption("Go back", () -> currentTab = UITab.Tasks),
                    });
                    break;
                case Tasks:
                    ui.CreateOptionMenu("Tasks", new CLIOption[] {
                            new CLIOption("View Tasks", () -> {
                                // Print all the tasks and allow the user to select a task
                                var tasks = taskManager.GetTasks();
                                CLIOption[] taskOptions = new CLIOption[tasks.size() + 1];
                                for (int i = 0; i < tasks.size(); i++) {
                                    Task task = tasks.get(i);
                                    taskOptions[i] = new CLIOption(task.getTask_name(), () -> {
                                        currentTab = UITab.SpecificTask;
                                        currentTask = task;
                                    });
                                }
                            }),
                    });
                    break;
                case Resident:
                    ui.CreateOptionMenu("Residents", new CLIOption[] {
                            new CLIOption("View Residents", () -> {
                                // Print all the residents
                                var users = database.getUsers();
                                var residents = new ArrayList<Resident>();

                                for (Person user : users) {
                                    if (user instanceof Resident) {
                                        residents.add((Resident) user);
                                    }
                                }
                                for (Person resident : residents) {
                                    ui.DisplayMessage(resident.toString());
                                }
                            }),
                            new CLIOption("Create Resident", () -> {
                                ui.DisplayMessage("Enter the resident name: ");
                                String residentName = ui.GetInputString();

                                ui.DisplayMessage("Enter the resident email: ");
                                String residentEmail = ui.GetInputString();

                                ui.DisplayMessage("Enter the resident password: ");
                                String residentPassword = ui.GetInputString();

                                ui.DisplayMessage("Enter the resident room number: ");
                                String residentRoomNumber = ui.GetInputString();

                                var resident = new Resident(residentName, residentEmail, residentRoomNumber,
                                        residentPassword,
                                        residentRoomNumber);

                                database.addItem(resident);

                                var emailData = new EmailData();
                                emailData.setRecipient(resident);
                                emailData.setSubject("Account created");
                                emailData.setText(
                                        "Your account has been created. You can now log in and change your password");

                                var notification = new Notification(emailData);
                                notification.send();

                                database.addItem(notification);
                                ui.DisplayMessage("Resident created successfully");
                            }),
                            new CLIOption("Go back", () -> currentTab = UITab.Notifications),
                    });
                default:
                    ui.CreateOptionMenu("", new CLIOption[] {
                            new CLIOption("Task", () -> currentTab = UITab.Tasks),
                            new CLIOption("Logout", () -> {
                                ui.DisplayMessage("Logging out...");
                                return;
                            }),
                    });
                    break;
            }

        }
    }

    public static void printTasks(CLIManager ui) {
        ui.DisplayMessage("Printing tasks...");

    }

}
