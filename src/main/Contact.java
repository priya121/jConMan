package main;

public class Contact {
    private String firstName;
    private String lastName;

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String newName) {
        firstName = newName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getLastName() {
        return lastName;
    }
}
