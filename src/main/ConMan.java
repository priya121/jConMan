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

    public void update(int contact, String newName) {
        Contact contactToUpdate = allContacts.get(contact - 1);
        contactToUpdate.setName(newName);
    }

    public void delete(int contactNumber) {
        allContacts.remove(contactNumber - 1);
    }

    public List<String> getAllNames() {
        List<String> listNames = new ArrayList<>();
        for (Contact contact : allContacts) {
            listNames.add(contact.getName());
        }
        return listNames;
    }

    public String numbersContacts() {
        String listOfContacts = "";
        int number = 1;
        for (String contact : getAllNames()) {
            listOfContacts += number + ") " + contact + "\n";
            number++;
        }
        return listOfContacts;
    }
}
