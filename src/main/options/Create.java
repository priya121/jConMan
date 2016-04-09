package main.options;

import main.Contact;

import java.util.List;

public class Create {
    private final List<Contact> allContacts;

    public Create(List<Contact> allContacts) {
        this.allContacts = allContacts;
    }

    public void add(Contact contact) {
        allContacts.add(contact);
    }
}
