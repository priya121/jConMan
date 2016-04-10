package main.options;

import main.Contact;
import main.inputoutput.InputOutput;

import java.util.List;

public class Update implements Option{
    private final List<Contact> allContacts;
    private final InputOutput inputOutput;

    public Update(List<Contact> allContacts, InputOutput inputOutput) {
        this.allContacts = allContacts;
        this.inputOutput = inputOutput;
    }

    @Override
    public void show() {
        inputOutput.showOutput("Update a contact's details: \n");
    }

    public void firstName(int contactNumber, String newName) {
        Contact contactToUpdate = allContacts.get(contactNumber - 1);
        contactToUpdate.setFirstName(newName);
    }

    public void lastName(int contactNumber, String newName) {
        Contact contactToUpdate = allContacts.get(contactNumber - 1);
        contactToUpdate.setLastName(newName);
    }

    public void email(int contactNumber, String newEmail) {
        Contact contactToUpdate = allContacts.get(contactNumber - 1);
        contactToUpdate.setEmailAddress(newEmail);
    }
}
