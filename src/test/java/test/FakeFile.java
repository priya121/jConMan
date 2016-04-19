package test;

import conMan.ContactList;
import conMan.contactfields.Contact;
import conMan.inputoutput.InputOutput;
import fileTypes.FileType;

import java.util.ArrayList;

public class FakeFile implements FileType {

    private final InputOutput console;
    private final ContactList allContacts;
    private final ContactList contactsToImport;
    public final ArrayList<Contact> savedContacts;

    public FakeFile(InputOutput console, ContactList allContacts, ContactList contactsToImport) {
        this.console = console;
        this.allContacts = allContacts;
        this.contactsToImport = contactsToImport;
        this.savedContacts = new ArrayList<>();
    }

    @Override
    public void importContacts() {
        for (Contact contact :contactsToImport.get()) {
            allContacts.addContact(contact);
        }
    }

    @Override
    public void saveContacts() {
        for (Contact contact : allContacts.get()) {
            savedContacts.add(contact);
        }
    }
}
