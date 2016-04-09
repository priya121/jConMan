package main;

import java.util.ArrayList;
import java.util.List;

public class ConMan {
    private String contact;
    private List<String> allContacts = new ArrayList<>();

    public void create(String name) {
        allContacts.add(name);
        contact = name;
    }

    public String read() {
        return contact;
    }

    public void update(String newName) {
        contact = newName;
    }

    public void delete() {
        contact = "";
    }

    public List<String> getAllContacts() {
        return allContacts;
    }

    public String numbersContacts() {
        String listOfContacts = "";
        int number = 1;
        for (String contact : allContacts) {
            listOfContacts += number + ") " + contact + "\n";
            number ++;
        }
        return listOfContacts;
    }
}
