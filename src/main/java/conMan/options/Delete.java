package conMan.options;

import conMan.ContactList;
import conMan.NameList;
import conMan.contactfields.Contact;
import conMan.inputoutput.InputOutput;
import conMan.inputoutput.ValidDigit;

import java.util.List;

public class Delete implements Option{
    private final ContactList allContacts;
    private final InputOutput console;
    private final NameList namesList;

    public Delete(ContactList allContacts, InputOutput console) {
        this.allContacts = allContacts;
        this.console = console;
        this.namesList = new NameList(allContacts, console);
    }

    @Override
    public void show() {
        console.showOutput("Delete a contact \n");
    }

    @Override
    public void perform() {
        showAllNames(namesList);
        filterNames();
        int chosenContact = getValidDigit() - 1;
        console.showOutput("Are you sure you want to delete this contact? (Y/N)\n");
        console.showOutput(allContacts.get(chosenContact).showFields());
        removeIfY(chosenContact);
    }

    private void filterNames() {
        List<Contact> filteredContacts = namesList.filterCheck();
        console.showOutput(namesList.formatNames(filteredContacts));
    }

    private void showAllNames(NameList names) {
        console.showOutput("Showing " + allContacts.getList().size() + " contacts" + "\n");
        console.showOutput(names.formatNames(allContacts.getList()));
    }

    private void removeIfY(int chosenContact) {
        if (console.takeInput().equals("Y")) {
            console.showOutput("Deleting Contact...\n");
            allContacts.getList().remove(chosenContact);
        } else {
            console.showOutput("Your contacts have not been changed\n");
        }
    }

    private int getValidDigit() {
        ValidDigit validDigit = new ValidDigit(console);
        return validDigit.getValidDigit();
    }
}
