package conMan.options;

import conMan.ContactList;
import conMan.NameList;
import conMan.inputoutput.InputOutput;
import conMan.inputoutput.ValidDigit;

public class Update implements Option {
    private final ContactList allContacts;
    private final InputOutput console;
    private final NameList contacts;

    public Update(ContactList allContacts, InputOutput console) {
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
        console.showOutput(contacts.formatNames(allContacts.getList()));
        int chosenContact = getValidDigit() - 1;
        console.showOutput("Fill in field to update or leave blank to keep previous value: \n");
        allContacts.get(chosenContact).updateFields();
    }

    private int getValidDigit() {
        ValidDigit validDigit = new ValidDigit(console);
        return validDigit.get(allContacts.getList().size());
    }
}
