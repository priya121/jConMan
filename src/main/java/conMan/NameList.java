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

    public List<Contact> filter() {
        List<Contact> filteredList = new ArrayList<>();
        Iterator<Contact> contactList = allContacts.getList().iterator();
        String letters = console.takeInput();
            while (contactList.hasNext()) {
                Contact contact = contactList.next();
                if (matchFound(letters, contact)) {
                    filteredList.add(contact);
                }
            }
        return filteredList;
    }

    private boolean matchFound(String letters, Contact contact) {
        return contact.getName().contains(letters);
    }
}
