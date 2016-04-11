package main;

import main.contactfields.Contact;

import java.util.List;

public class NameList {
    private final List<Contact> allContacts;

    public NameList(List<Contact> allContacts) {
        this.allContacts = allContacts;
    }

    public String listAllNames() {
        String listOfContacts = "";
        int number = 1;
        for (Contact contact : allContacts) {
            listOfContacts += number + ") " + contact.getName() + "\n";
            number++;
        }
        return listOfContacts;
    }
}
