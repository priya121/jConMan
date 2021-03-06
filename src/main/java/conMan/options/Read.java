package conMan.options;

import conMan.ContactList;
import conMan.NameList;
import conMan.contactfields.Contact;
import conMan.inputoutput.InputOutput;
import conMan.inputoutput.ValidDigit;

import java.util.List;

public class Read implements Option {
    private final ContactList allContacts;
    private final InputOutput console;
    private final NameList namesList;
    private final ValidDigit validDigit;

    public Read(ContactList allContacts, InputOutput console) {
        this.allContacts = allContacts;
        this.console = console;
        this.namesList = new NameList(allContacts, console);
        this.validDigit = new ValidDigit(console);
    }

    @Override
    public void show() {
        console.showOutput("Read a contact's details \n");
    }

    @Override
    public void perform() {
        if (contactsExist() && contactsNecessary()) {
            namesList.display();
            List<Contact> filtered = namesList.filterCheck();
            namesList.show(filtered);
            showSelectedContact(filtered);
        } else {
            console.showOutput("There are no contacts to display.\n\n");
        }
        namesList.backToMainMenu();
    }

    @Override
    public boolean contactsNecessary() {
        return true;
    }

    @Override
    public boolean contactsExist() {
        return allContacts.get().size() > 0;
    }

    private void showSelectedContact(List<Contact> filteredContacts) {
        int userDigit = validDigit.get(filteredContacts.size());
        Contact selected = filteredContacts.get(userDigit - 1);
        console.clearScreen();
        console.showOutput(selected.showFields());
    }

}
