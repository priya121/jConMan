package conMan.options;

import conMan.ContactList;
import conMan.NameList;
import conMan.inputoutput.InputOutput;
import conMan.inputoutput.ValidDigit;

public class Delete implements Option{
    private final ContactList allContacts;
    private final InputOutput console;
    private final NameList nameList;

    public Delete(ContactList allContacts, InputOutput console) {
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
        console.showOutput(nameList.listNames(allContacts.getList()));
        int chosenContact = getValidDigit() - 1;
        console.showOutput("Are you sure you want to delete this contact? (Y/N)");
        removeIfY(chosenContact);
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
