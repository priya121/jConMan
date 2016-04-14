package main.options;

import main.contactfields.Contact;
import main.inputoutput.InputOutput;

import java.util.List;

public class Create implements Option {
    private final List<Contact> allContacts;
    private final InputOutput console;

    public Create(List<Contact> allContacts, InputOutput console) {
        this.allContacts = allContacts;
        this.console = console;
    }

    @Override
    public void show() {
        console.showOutput("Create a contact \n");
    }

    @Override
    public void perform() {
        Contact newContact = new Contact(console);
        newContact.setFields();
        allContacts.add(newContact);
    }
}
