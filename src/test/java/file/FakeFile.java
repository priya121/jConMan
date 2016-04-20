package file;

import conMan.ContactList;
import conMan.contactfields.Contact;
import fileTypes.FileType;

import java.util.ArrayList;

public class FakeFile implements FileType {

    private final ContactList allContacts;
    private final ContactList contactsToImport;
    public final ArrayList<Contact> savedContacts;

    public FakeFile(ContactList allContacts, ContactList contactsToImport) {
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
