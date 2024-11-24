public class Person {

    private String Name;
    private String Role;
    private String email;

    public Person(String name, String role, String email) {
        this.Name = name;
        this.Role = role;
        this.email = email;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name){
        this.Name = name;

    }
    public String getEmail() {
    	return email;
    }

}
