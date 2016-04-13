package main;

import main.contactfields.Contact;
import main.inputoutput.InputOutput;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NameList {
    private final List<Contact> allContacts;
    private final InputOutput console;

    public NameList(List<Contact> allContacts, InputOutput console) {
        this.allContacts = allContacts;
        this.console = console;
    }

    public String listNames(List<Contact> contacts) {
        String listOfContacts = "";
        int number = 1;
        for (Contact contact : contacts) {
            listOfContacts += number + ") " + contact.getName() + "\n";
            number++;
        }
        return listOfContacts;
    }

    public List<Contact> filter() {
        Iterator<Contact> contactList = allContacts.iterator();
        List<Contact> filteredList = new ArrayList<>();
        String filterCharacters = console.takeInput();
        while (contactList.hasNext()) {
            Contact contact = contactList.next();
            if (contact.getName().contains(filterCharacters))
                filteredList.add(contact);
        }
        return filteredList;
    }
}
