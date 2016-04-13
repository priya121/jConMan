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
        List<Contact> filteredList = new ArrayList<>();
        Iterator<Contact> contactList = allContacts.iterator();
        char letter = 0;
        while (letter != 'q') {
            letter = (char) console.takeChar();
            while (contactList.hasNext()) {
                Contact contact = contactList.next();
                filteredList = matchFound(letter, contact);
            }
        }
        return filteredList;
    }

    private List<Contact> matchFound(char letter, Contact contact) {
        List<Contact> filteredList;
        filteredList = new ArrayList<>();
        if (contact.getName().contains("" + letter))
            filteredList.add(contact);
        return filteredList;
    }
}
