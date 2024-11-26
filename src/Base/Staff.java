package Base;

public class Staff extends Person {
    public Staff(String name, String username, String password) {
        super(name, username, password, "staff");  // The role is "staff" for this class
    }

    @Override
    public String toString() {
        return "Staff: " + getUsername() + ", Role: " + getRole();
    }
}