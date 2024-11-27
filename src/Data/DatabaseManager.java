package Data;

import java.io.*;
import java.util.ArrayList;

import Application.Notification.Notification;
import Base.Person;
import Business.Task;

public class DatabaseManager {
    enum Database {
        USERS, TASKS, NOTIFICATIONS
    }

    private ArrayList<Person> userDatabase = new ArrayList<>();
    private ArrayList<Task> taskDatabase = new ArrayList<>();
    private ArrayList<Notification> notificationDatabase = new ArrayList<>();

    private static final String USER_FILE = "users.dat";
    private static final String TASK_FILE = "tasks.dat";
    private static final String NOTIFICATION_FILE = "notifications.dat";

    public DatabaseManager() {
        // Load data from files when initializing
        userDatabase = loadDatabase(USER_FILE, new ArrayList<Person>());
        taskDatabase = loadDatabase(TASK_FILE, new ArrayList<Task>());
        notificationDatabase = loadDatabase(NOTIFICATION_FILE, new ArrayList<Notification>());
    }

    public void addItem(Person person) {
        userDatabase.add(person);
        saveDatabase(USER_FILE, userDatabase);
    }

    public void addItem(Task task) {
        taskDatabase.add(task);
        saveDatabase(TASK_FILE, taskDatabase);
    }

    public void addItem(Notification notification) {
        notificationDatabase.add(notification);
        saveDatabase(NOTIFICATION_FILE, notificationDatabase);
    }

    public Object getItem(Database database, int id) {
        ArrayList<?> dataPoints;
        if (database == Database.USERS) {
            dataPoints = userDatabase;
        } else if (database == Database.TASKS) {
            dataPoints = taskDatabase;
        } else {
            dataPoints = notificationDatabase;
        }

        for (Object datum : dataPoints) {
            if (datum.hashCode() == id) return datum;
        }
        return null;
    }

    // Generic method to save a database to a .dat file
    private <T> void saveDatabase(String fileName, ArrayList<T> database) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(database);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Generic method to load a database from a .dat file
    @SuppressWarnings("unchecked")
    private <T> ArrayList<T> loadDatabase(String fileName, ArrayList<T> defaultDatabase) {
        File file = new File(fileName);
        if (!file.exists()) {
            return defaultDatabase;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return defaultDatabase;
    }
}
