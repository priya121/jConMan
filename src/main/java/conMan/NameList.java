package conMan;

import conMan.contactfields.Contact;
import conMan.inputoutput.InputOutput;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NameList {
    private final ContactList allContacts;
    private final InputOutput console;

    public NameList(ContactList allContacts, InputOutput console) {
        this.allContacts = allContacts;
        this.console = console;
    }

    public String formatNames(List<Contact> contacts) {
        String listOfContacts = "";
        int number = 1;
        for (Contact contact : contacts) {
            listOfContacts += number + ") " + contact.getName() + "\n";
            number++;
        }
        return listOfContacts;
    }

    public List<Contact> filterCheck() {
        console.showOutput("\nWould you like to filter contacts by name? (Y/N) \n");
        if (console.takeInput().contains("Y")) {
            console.showOutput("Enter a name to filter: \n");
            List<Contact> filtered = filter();
            if (filteredContactFound(filtered)) return filtered;
        }
        return allContacts.getList();
    }

    private boolean filteredContactFound(List<Contact> filtered) {
        if (filtered.size() > 0) {
            return true;
        } else {
            console.showOutput("There are no contacts with that name\n");
            filterCheck();
        }
        return false;
    }

    public List<Contact> filter() {
        List<Contact> filteredList = new ArrayList<>();
        Iterator<Contact> contactList = allContacts.getList().iterator();
        String letters = console.takeInput();
        checkContacts(filteredList, contactList, letters);
        return filteredList;
    }

    private void checkContacts(List<Contact> filteredList, Iterator<Contact> contactList, String letters) {
        while (contactList.hasNext()) {
            Contact contact = contactList.next();
            if (matchFound(letters, contact)) {
                filteredList.add(contact);
            }
        }
    }

    private boolean matchFound(String letters, Contact contact) {
        return contact.getName().contains(letters);
    }
}
