package conMan.options;

import conMan.NameList;
import conMan.contactfields.Contact;
import conMan.inputoutput.InputOutput;
import conMan.inputoutput.ValidDigit;

import java.util.List;

public class Delete implements Option{
    private final List<Contact> allContacts;
    private final InputOutput console;
    private final NameList nameList;

    public Delete(List<Contact> allContacts, InputOutput console) {
        this.allContacts = allContacts;
        this.console = console;
        this.nameList = new NameList(allContacts, console);
    }

    @Override
    public void show() {
        console.showOutput("Delete a contact \n");
    }

    @Override
    public void perform() {
        console.showOutput(nameList.listNames(allContacts));
        int chosenContact = getValidDigit() - 1;
        allContacts.remove(chosenContact);
    }

    private int getValidDigit() {
        ValidDigit validDigit = new ValidDigit(console);
        return validDigit.getValidDigit();
    }
}
