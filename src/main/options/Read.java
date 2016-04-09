package main.options;

import main.Contact;

import java.util.List;

public class Read {
    private final List<Contact> allContacts;

    public Read(List<Contact> allContacts)  {
      this.allContacts = allContacts;
   }

    public String name(int contactNumber) {
        return allContacts.get(contactNumber - 1).getName();
    }

    public String contact(int contactNumber) {
        Contact selected = allContacts.get(contactNumber - 1);
        return "Name: " + selected.getName() + "\n" +
                "Email: " + selected.getEmail() + "\n";
    }
}
