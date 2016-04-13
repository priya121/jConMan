package main.options;

import main.NameList;
import main.contactfields.Contact;
import main.inputoutput.InputOutput;
import main.inputoutput.ValidDigit;

import java.util.List;

public class Read implements Option {
    private final List<Contact> allContacts;
    private final InputOutput inputOutput;

    public Read(List<Contact> allContacts, InputOutput inputOutput) {
        this.allContacts = allContacts;
        this.inputOutput = inputOutput;
    }

    @Override
    public void show() {
        inputOutput.showOutput("Read a contact's details \n");
    }

    @Override
    public void perform() {
        listAllNames();
        int chosenNumber = getValidDigit();
        inputOutput.showOutput(contact(chosenNumber));
    }

    public String contact(int contactNumber) {
        Contact selected = allContacts.get(contactNumber - 1);
        return selected.showFields();
    }

    private void listAllNames() {
        NameList names = new NameList(allContacts, inputOutput);
        inputOutput.showOutput("Select a contact to view: \n");
        inputOutput.showOutput(names.listNames(allContacts));
    }

    private int getValidDigit() {
        ValidDigit validDigit = new ValidDigit(inputOutput);
        return validDigit.getValidDigit();
    }
}
