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
        if (contactsExist() && contactsNecessary()) {
            console.showOutput(contacts.formatNames(allContacts.get()));
            int chosenContact = getValidDigit() - 1;
            console.showOutput("Fill in field to update or leave blank to keep previous value: \n");
            allContacts.getContact(chosenContact).updateFields();
        } else {
            console.showOutput("There are no contacts to update.\n\n");
        }
    }

    @Override
    public boolean contactsExist() {
        return allContacts.get().size() > 0;
    }

    @Override
    public boolean contactsNecessary() {
        return true;
    }

    private int getValidDigit() {
        ValidDigit validDigit = new ValidDigit(console);
        return validDigit.get(allContacts.get().size());
    }
}
