package classesHelpers;
public class User {
    final int id;
    final String lastName;
    final String firstName;
    public User(int id ,String lastName, String firstName){
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


}