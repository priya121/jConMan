package main.options;

import main.NameList;
import main.contactfields.Contact;
import main.inputoutput.InputOutput;
import main.inputoutput.ValidDigit;

import java.util.List;

public class Update implements Option {
    private final List<Contact> allContacts;
    private final InputOutput console;
    private final NameList contacts;

    public Update(List<Contact> allContacts, InputOutput console) {
        this.allContacts = allContacts;
        this.console = console;
        this.contacts = new NameList(allContacts, console);
    }

    @Override
    public void show() {
        console.showOutput("Update a contact's details \n");
    }

    @Override
    public void perform() {
        console.showOutput(contacts.listNames(allContacts));
        int chosenContact = getValidDigit() - 1;
        console.showOutput("Fill in field to update or leave blank to keep previous value: \n");
        allContacts.get(chosenContact).updateFields();
    }

    private int getValidDigit() {
        ValidDigit validDigit = new ValidDigit(console);
        return validDigit.getValidDigit();
    }
}
