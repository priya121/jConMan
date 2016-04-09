package main;

import java.util.ArrayList;
import java.util.List;

public class ConMan {
    private List<Contact> allContacts = new ArrayList<>();

    public void create(Contact contact) {
        allContacts.add(contact);
    }

    public String read(int contactNumber) {
        return allContacts.get(contactNumber - 1).getName();
    }

    public void updateFirstName(int contactNumber, String newName) {
        Contact contactToUpdate = allContacts.get(contactNumber - 1);
        contactToUpdate.setFirstName(newName);
    }

    public void updateLastName(int contactNumber, String newName) {
        Contact contactToUpdate = allContacts.get(contactNumber - 1);
        contactToUpdate.setLastName(newName);
    }

    public void delete(int contactNumber) {
        allContacts.remove(contactNumber - 1);
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
