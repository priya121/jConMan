package conMan;

import conMan.contactfields.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactList {
    List<Contact> allContacts = new ArrayList<>();

    public void addContact(Contact contact) {
        allContacts.add(contact);
    }

    public Contact getContact(int contactNumber) {
        return allContacts.get(contactNumber);
    }

    public List<Contact> get() {
        return allContacts;
    }

    public List<Contact> contactsToDisplay() {
        if (allContacts.size() >= 15) {
            List<Contact> firstFifteen = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                firstFifteen.add(allContacts.get(i));
            }
            return firstFifteen;
        } else {
            return allContacts;
        }
    }
}
