package main;

public class Contact {
    private String firstName;
    private String lastName;
    private String email;

    public Contact(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setFirstName(String newName) {
        firstName = newName;
    }

    public void setLastName(String newName) {
        lastName = newName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public void setEmailAddress(String newEmail) {
       email = newEmail;
    }

    public String getEmail() {
        return email;
    }
}
