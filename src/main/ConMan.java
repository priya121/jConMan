package main;

import java.util.ArrayList;
import java.util.List;

public class ConMan {
    private List<String> allContacts = new ArrayList<>();

    public void create(Contact contact) {
        allContacts.add(contact.getName());
    }

    public String read(int contactNumber) {
        return allContacts.get(contactNumber - 1);
    }

    public void update(int updateContact, String newName) {
        allContacts.set(updateContact - 1, newName);
    }

    public void delete(int contactNumber) {
        allContacts.remove(contactNumber - 1);
    }

    public List<String> getAllContacts() {
        return allContacts;
    }

    public String numbersContacts() {
        String listOfContacts = "";
        int number = 1;
        for (String contact : allContacts) {
            listOfContacts += number + ") " + contact + "\n";
            number++;
        }
        return listOfContacts;
    }
}
