package conMan.contactfields;

import conMan.inputoutput.InputOutput;

import java.util.Arrays;
import java.util.List;

public class Contact {
    public List<Field> fields;
    private InputOutput console;
    private FirstName firstName;
    private LastName lastName;
    private Email email;
    private HomeAddress homeAddress;

    public Contact(InputOutput console) {
        this.console = console;
        this.firstName = new FirstName(console);
        this.lastName = new LastName(console);
        this.email = new Email(console);
        this.homeAddress = new HomeAddress(console);
        this.fields = Arrays.asList(firstName, lastName, email, homeAddress);
    }

    public Contact(String firstName, String lastName, String email, String homeAddress) {
        this.firstName = new FirstName(firstName);
        this.lastName = new LastName(lastName);
        this.email = new Email(email);
        this.homeAddress = new HomeAddress(homeAddress);
        this.fields = Arrays.asList(this.firstName, this.lastName, this.email, this.homeAddress);
    }

    public void setFields() {
        for (Field field : fields) {
            console.showOutput(field.showFieldName());
            field.set();
        }
    }

    public void updateFields() {
        for (Field field : fields) {
            console.showOutput(field.showFieldName());
            field.update();
        }
    }

    public String showFields() {
        String list = "";
        for (Field field : fields) {
            list += field.showFieldName() + field.show() + "\n";
        }
        return list + "\n" + "\n";
    }

    public void setExisting() {
        for (Field field : fields) {
            field.setExisting();
        }
    }

    public String getName() {
        return firstName.show() + " " + lastName.show();
    }

    public String getEmail() {
        return email.show();
    }
}
