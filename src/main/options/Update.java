package main.options;

import main.NameList;
import main.contactfields.Contact;
import main.inputoutput.InputOutput;
import main.inputoutput.ValidDigit;

import java.util.List;

public class Update implements Option {
    private final List<Contact> allContacts;
    private final InputOutput inputOutput;

    public Update(List<Contact> allContacts, InputOutput inputOutput) {
        this.allContacts = allContacts;
        this.inputOutput = inputOutput;
    }

    @Override
    public void show() {
        inputOutput.showOutput("Update a contact's details \n");
    }

    @Override
    public void perform() {
        NameList contacts = new NameList(allContacts, inputOutput);
        inputOutput.showOutput(contacts.listNames(allContacts));
        int chosenContact = getValidDigit() - 1;
        allContacts.get(chosenContact).setFields();
    }

    private int getValidDigit() {
        ValidDigit validDigit = new ValidDigit(inputOutput);
        return validDigit.getValidDigit();
    }
}
