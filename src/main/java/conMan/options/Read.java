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
        validDigit = new ValidDigit(console);
    }

    @Override
    public void show() {
        console.showOutput("Read a contact's details \n");
    }

    @Override
    public void perform() {
        if (allContacts.getList().size() > 0) {
            showAllNames(namesList);
            List<Contact> filtered = namesList.filterCheck();
            console.showOutput(namesList.formatNames(filtered));
            showSelectedContact(filtered);
        } else {
            console.showOutput("There are no contacts to display.\n\n");
        }
    }

    private void showSelectedContact(List<Contact> filteredContacts) {
        int userDigit = validDigit.get(filteredContacts.size());
        Contact selected = filteredContacts.get(userDigit - 1);
        console.showOutput(selected.showFields());
    }

    private void showAllNames(NameList names) {
        console.showOutput("Showing " + allContacts.getList().size() + " contacts" + "\n");
        console.showOutput(names.formatNames(allContacts.getList()));
    }
}
