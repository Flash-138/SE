package Base;

public class Resident extends Person {
    private String roomNumber;

    public Resident(String name, String username, String password, String roomNumber) {
        super(name, username, password, "resident");  // The role is "resident" for this class
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public String toString() {
        return "Resident: " + getUsername() + ", Room: " + roomNumber + ", Role: " + getRole();
    }
}