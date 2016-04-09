package main;

import java.util.ArrayList;
import java.util.List;

public class ConMan {
    
    private List<Contact> allContacts = new ArrayList<>();
    Update update = new Update(allContacts);

    public void create(Contact contact) {
        allContacts.add(contact);
    }

    public String readName(int contactNumber) {
        return allContacts.get(contactNumber - 1).getName();
    }

    public String readContact(int contactNumber) {
        Contact selected = allContacts.get(contactNumber - 1);
        return "Name: " + selected.getName() + "\n" +
               "Email: " + selected.getEmail() + "\n";
    }

    public void updateFirstName(int contactNumber, String newName) {
        update.firstName(contactNumber, newName);
    }

    public void updateLastName(int contactNumber, String newName) {
        update.lastName(contactNumber, newName);
    }

    public void updateEmail(int contactNumber, String newEmail) {
        update.email(contactNumber, newEmail);
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
