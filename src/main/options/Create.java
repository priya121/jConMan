package main.options;

import main.Contact;
import main.inputoutput.InputOutput;

import java.util.List;

public class Create implements Option {
    private final List<Contact> allContacts;
    private final InputOutput inputOutput;

    public Create(List<Contact> allContacts, InputOutput inputOutput) {
        this.allContacts = allContacts;
        this.inputOutput = inputOutput;
    }

    @Override
    public void show() {
        inputOutput.showOutput("Create a contact: \n");
    }

    public void add(Contact contact) {
        allContacts.add(contact);
    }

}
