package conMan.options;

import conMan.ContactList;
import conMan.NameList;
import conMan.contactfields.Contact;
import conMan.inputoutput.InputOutput;
import conMan.inputoutput.ValidDigit;

import java.util.List;

public class Update implements Option {
    private final ContactList allContacts;
    private final InputOutput console;
    private final NameList namesList;
    private final ValidDigit validDigit;

    public Update(ContactList allContacts, InputOutput console) {
        this.allContacts = allContacts;
        this.console = console;
        this.namesList = new NameList(allContacts, console);
        this.validDigit = new ValidDigit(console);
    }

    @Override
    public void show() {
        console.showOutput("Update a contact's details \n");
    }

    @Override
    public void perform() {
        if (contactsExist() && contactsNecessary()) {
            namesList.display();
            List<Contact> filtered = namesList.filterCheck();
            namesList.show(filtered);
            int chosenContact = getValidDigit() - 1;
            updateDetails(filtered, chosenContact);
        } else {
            console.showOutput("There are no contacts to update.\n\n");
        }
        namesList.backToMainMenu();
    }

    private void updateDetails(List<Contact> filtered, int chosenContact) {
        console.clearScreen();
        console.showOutput("Fill in field to update or leave blank to keep previous value: \n");
        filtered.get(chosenContact).updateFields();
        console.clearScreen();
        console.showOutput(filtered.get(chosenContact).showFields());
    }

    @Override
    public boolean contactsExist() {
        return allContacts.get().size() > 0;
    }

    @Override
    public boolean contactsNecessary() {
        return true;
    }

    private int getValidDigit() {
        return validDigit.get(allContacts.get().size());
    }

}
