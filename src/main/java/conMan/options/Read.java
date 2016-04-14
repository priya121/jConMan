package conMan.options;

import conMan.NameList;
import conMan.contactfields.Contact;
import conMan.inputoutput.InputOutput;
import conMan.inputoutput.ValidDigit;

import java.util.List;

public class Read implements Option {
    private final List<Contact> allContacts;
    private final InputOutput console;

    public Read(List<Contact> allContacts, InputOutput console) {
        this.allContacts = allContacts;
        this.console = console;
    }

    @Override
    public void show() {
        console.showOutput("Read a contact's details \n");
    }

    @Override
    public void perform() {
        listAllNames();
        int chosenNumber = getValidDigit();
        console.showOutput(contact(chosenNumber));
    }

    public String contact(int contactNumber) {
        Contact selected = allContacts.get(contactNumber - 1);
        return selected.showFields();
    }

    private void listAllNames() {
        NameList names = new NameList(allContacts, console);
        console.showOutput("Select a contact to view: \n");
        console.showOutput(names.listNames(allContacts));
    }

    private int getValidDigit() {
        ValidDigit validDigit = new ValidDigit(console);
        return validDigit.getValidDigit();
    }
}
