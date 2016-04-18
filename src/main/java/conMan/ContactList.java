package conMan;

import conMan.contactfields.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactList {
    List<Contact> allContacts = new ArrayList<>();

    public void addContact(Contact contact) {
        allContacts.add(contact);
    }

    public Contact get(int contactNumber) {
        return allContacts.get(contactNumber);
    }

    public List<Contact> getList() {
        return allContacts;
    }
}
