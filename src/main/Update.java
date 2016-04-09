package main;

import java.util.List;

public class Update {
    private final List<Contact> allContacts;

    public Update(List<Contact> allContacts) {
        this.allContacts = allContacts;
    }

    public void firstName(int contactNumber, String newName) {
        Contact contactToUpdate = allContacts.get(contactNumber - 1);
        contactToUpdate.setFirstName(newName);
    }

    public void lastName(int contactNumber, String newName) {
        Contact contactToUpdate = allContacts.get(contactNumber - 1);
        contactToUpdate.setLastName(newName);
    }

    public void email(int contactNumber, String newEmail) {
        Contact contactToUpdate = allContacts.get(contactNumber - 1);
        contactToUpdate.setEmailAddress(newEmail);
    }
}
