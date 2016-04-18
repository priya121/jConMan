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

    public Read(ContactList allContacts, InputOutput console) {
        this.allContacts = allContacts;
        this.console = console;
    }

    @Override
    public void show() {
        console.showOutput("Read a contact's details \n");
    }

    @Override
    public void perform() {
        NameList names = new NameList(allContacts, console);
        showAllNames(names);
        List<Contact> filteredContacts = filter(names);
        console.showOutput(names.formatNames(filteredContacts));
        Contact selected = filteredContacts.get(getValidDigit() - 1);
        console.showOutput(selected.showFields());
    }

    private void showAllNames(NameList names) {
        console.showOutput("Showing " + allContacts.getList().size() + " contacts" + "\n");
        console.showOutput(names.formatNames(allContacts.getList()));
    }

    private List<Contact> filter(NameList names) {
        console.showOutput("Would you like to filter contacts by name? (Y/N) \n");
        if (console.takeInput().contains("Y")) {
            console.showOutput("Enter a name to filter: \n");
            return names.filter();
        } else {
            return allContacts.getList();
        }
    }

    private int getValidDigit() {
        ValidDigit validDigit = new ValidDigit(console);
        return validDigit.getValidDigit();
    }
}
