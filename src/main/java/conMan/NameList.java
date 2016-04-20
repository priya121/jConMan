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
        console.showOutput("\nWould you like to filter contacts by name?\n" +
                           "(Y) to filter / Any other key to choose from list\n");
        if (console.takeInput().contains("Y")) {
            console.showOutput("Enter a name to filter: \n");
            List<Contact> filtered = filter();
            console.clearScreen();
            if (contactFound(filtered)) return filtered;
        }
        return allContacts.contactsToDisplay();
    }

    public List<Contact> filter() {
        List<Contact> filteredList = new ArrayList<>();
        Iterator<Contact> contactList = allContacts.get().iterator();
        String letters = console.takeInput();
        checkContacts(filteredList, contactList, letters);
        return filteredList;
    }

    private boolean contactFound(List<Contact> filtered) {
        if (filtered.size() > 0) {
            return true;
        } else {
            console.showOutput("There are no contacts with that name\n");
            filterCheck();
        }
        return false;
    }

    private void checkContacts(List<Contact> filteredList, Iterator<Contact> contactList, String letters) {
        while (contactList.hasNext()) {
            Contact contact = contactList.next();
            if (matchFound(letters, contact)) {
                filteredList.add(contact);
            }
        }
    }

    public void show(List<Contact> filtered) {
        console.clearScreen();
        console.showOutput("Choose a contact by number: \n");
        console.showOutput(formatNames(filtered));
    }

    public void mainMenu() {
        console.showOutput("Hit any key to go back to the main menu.");
        console.takeInput();
        console.clearScreen();
    }

    private boolean matchFound(String letters, Contact contact) {
        return contact.getName().contains(letters);
    }
}
