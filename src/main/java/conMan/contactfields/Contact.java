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
    private Phone phone;
    private Website website;

    public Contact(InputOutput console) {
        this.console = console;
        this.firstName = new FirstName(console);
        this.lastName = new LastName(console);
        this.email = new Email(console);
        this.homeAddress = new HomeAddress(console);
        this.phone = new Phone(console);
        this.website = new Website(console);
        this.fields = Arrays.asList(firstName, lastName, email, homeAddress, phone, website);
    }

    public Contact(String firstName, String lastName, String email, String homeAddress, String phone, String website, InputOutput console) {
        this.console = console;
        this.firstName = new FirstName(firstName, this.console);
        this.lastName = new LastName(lastName, this.console);
        this.email = new Email(email, this.console);
        this.homeAddress = new HomeAddress(homeAddress, this.console);
        this.phone = new Phone(phone, this.console);
        this.website = new Website(website, this.console);
        this.fields = Arrays.asList(this.firstName, this.lastName, this.email, this.homeAddress, this.phone, this.website);
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
            console.showOutput("(previous) " + field.get() + "\n");
            field.update();
        }
    }

    public String showFields() {
        String list = "";
        for (Field field : fields) {
            list += field.showFieldName() + field.get() + "\n";
        }
        return list + "\n" + "\n";
    }

    public void setExisting() {
        for (Field field : fields) {
            field.setExisting();
        }
    }

    public String getName() {
        return firstName.get() + " " + lastName.get();
    }
}
