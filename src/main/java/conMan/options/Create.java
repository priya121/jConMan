package conMan.options;

import conMan.ContactList;
import conMan.NameList;
import conMan.contactfields.Contact;
import conMan.inputoutput.InputOutput;

public class Create implements Option {
    private final ContactList allContacts;
    private final InputOutput console;
    private final NameList namesList;

    public Create(ContactList allContacts, InputOutput console) {
        this.allContacts = allContacts;
        this.console = console;
        this.namesList = new NameList(allContacts, console);
    }

    @Override
    public void show() {
        console.showOutput("Create a contact \n");
    }

    @Override
    public void perform() {
        if (!contactsNecessary() && !contactsNecessary()) {
            Contact newContact = new Contact(console);
            newContact.setFields();
            allContacts.addContact(newContact);
        }
        namesList.backToMainMenu();
    }

    @Override
    public boolean contactsNecessary() {
        return false;
    }

    @Override
    public boolean contactsExist() {
        return false;
    }
}
