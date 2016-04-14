package conMan.options;

import conMan.ContactList;
import conMan.contactfields.Contact;
import conMan.inputoutput.InputOutput;

public class Create implements Option {
    private final ContactList allContacts;
    private final InputOutput console;

    public Create(ContactList allContacts, InputOutput console) {
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
        allContacts.addContact(newContact);
    }
}
