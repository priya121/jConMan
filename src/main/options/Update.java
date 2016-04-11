package main.options;

import main.contactfields.Contact;
import main.NameList;
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

    @Override
    public void perform() {
        NameList contacts = new NameList(allContacts);
        inputOutput.showOutput(contacts.listAllNames());
        int chosenContact = Integer.parseInt(inputOutput.takeInput()) - 1;
        allContacts.get(chosenContact).setFields();
    }
}
