package main;

import java.util.List;

public class Delete {
    private final List<Contact> allContacts;

    public Delete(List<Contact> allContacts) {
        this.allContacts = allContacts;
    }

    public void delete(int contactNumber) {
        allContacts.remove(contactNumber);
    }
}
