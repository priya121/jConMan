package main.options;

import main.Contact;
import main.inputoutput.InputOutput;

import java.util.List;

public class Delete implements Option{
    private final List<Contact> allContacts;
    private final InputOutput inputOutput;

    public Delete(List<Contact> allContacts, InputOutput inputOutput) {
        this.allContacts = allContacts;
        this.inputOutput = inputOutput;
    }

    @Override
    public void show() {
        inputOutput.showOutput("Delete a contact: \n");
    }

    public void delete(int contactNumber) {
        allContacts.remove(contactNumber);
    }
}
