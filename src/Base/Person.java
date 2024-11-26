package Base;

public class Person {

    private String Name;
    private String Role;
    private String Email;
    


    public Person(String name, String role) {
        this.Name = name;
        this.Role = role;
        this.Email = "email@gmail.com";

    }

    public String getName() {
        return Name;
    }
    public void setName(String name){
        this.Name = name;
    }
    public String getEmail() {
    	return this.Email;
    }

}
