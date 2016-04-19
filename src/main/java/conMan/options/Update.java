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
            showAllNames(namesList);
            filterNames();
            int chosenContact = getValidDigit() - 1;
            console.clearScreen();
            updateDetails(chosenContact);
        } else {
            console.showOutput("There are no contacts to update.\n\n");
        }
        mainMenu();
    }

    private void filterNames() {
        List<Contact> filtered = namesList.filterCheck();
        console.showOutput(namesList.formatNames(filtered));
    }

    private void mainMenu() {
        console.showOutput("Hit any key to go back to the main menu.");
        console.takeInput();
        console.clearScreen();
    }

    private void updateDetails(int chosenContact) {
        console.showOutput("Fill in field to update or leave blank to keep previous value: \n");
        allContacts.getContact(chosenContact).updateFields();
        console.clearScreen();
        console.showOutput(allContacts.getContact(chosenContact).showFields());
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

    private void showAllNames(NameList names) {
        console.showOutput("Showing " + allContacts.get().size() + " contacts" + "\n");
        console.showOutput(names.formatNames(allContacts.get()));
    }
}
