public class Person {

    private String Name;
    private String Role;

    public Person(String name, String role) {
        this.Name = name;
        this.Role = role;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name){
        this.Name = name;

    }

    public void setRole(String role) {

        Role = role;
    }

    public String getRole() {
        return Role;
    }
}
