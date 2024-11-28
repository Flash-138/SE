package Base;

import Business.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    private String Name;
    private String username;
    private String password;
    private String role; // Role attribute (e.g., "admin", "user")
    private ArrayList<Task> tasks;
    public String Email;

    // Constructor
    public Person(String Name, String email, String username, String password, String role) {
        this.username = username;
        this.Email = email;
        this.password = password;
        this.Name = Name;
        this.role = role;
        this.tasks = new ArrayList<>();
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    @Override
    public String toString() {
        return "Name: " + Name + ",Username: " + username + ", Tasks: " + tasks.size();
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }
}
