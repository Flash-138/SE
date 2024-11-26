package Base;

import java.util.ArrayList;
import java.util.List;

public class Login {
    private List<Person> users;

    public Login() {
        users = new ArrayList<>();
    }

    // Create a new user and store it in the "database"
    public void createUser(String Name, String username, String password,String roleKey) {
        // Check if the username already exists
        for (Person user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists.");
                return;
            }
        }
        
        if (roleKey.equals("adminKey")) {
            Staff newStaff = new Staff(Name, username, password);
            users.add(newStaff);
            System.out.println("Staff user created successfully.");
        }
        // Check if roleKey is a room number format (for Resident)
        else if (roleKey.matches("[IJGH]\\d{4}")) {  // Matches I1234, J5678, G4321, H8765
            String roomPrefix = roleKey.substring(0, 1);  // Extracts 'I', 'J', 'G', or 'H'
            String roomNumber = roleKey.substring(1);     // Extracts the numeric part (e.g., 1234)
            Resident newResident = new Resident(Name, username, password, roomPrefix + roomNumber);
            users.add(newResident);
        } else {
            System.out.println("Invalid Role Key. User not created.");
        }
    }

    // Delete a user from the "database"
    public void deleteUser(String username) {
        Person userToDelete = null;
        for (Person user : users) {
            if (user.getUsername().equals(username)) {
                userToDelete = user;
                break;
            }
        }

        if (userToDelete != null) {
            users.remove(userToDelete);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    // Login a user by validating username and password
    public boolean login(String username, String password) {
        for (Person user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful. Welcome, " + username + "!");
                return true;  // Login successful
            }
        }
        System.out.println("Invalid username or password.");
        return false;  // Login failed
    }

    // Show all users in the "database" (for debugging purposes)
    public void showAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users in the system.");
        } else {
            for (Person user : users) {
                System.out.println(user);
            }
        }
    }
}