package main.options;

import main.NameList;
import main.contactfields.Contact;
import main.inputoutput.InputOutput;
import main.inputoutput.ValidDigit;

import java.util.List;

public class Delete implements Option{
    private final List<Contact> allContacts;
    private final InputOutput inputOutput;
    private final NameList nameList;

    public Delete(List<Contact> allContacts, InputOutput inputOutput) {
        this.allContacts = allContacts;
        this.inputOutput = inputOutput;
        this.nameList = new NameList(allContacts, inputOutput);
    }

    @Override
    public void show() {
        inputOutput.showOutput("Delete a contact \n");
    }

    @Override
    public void perform() {
        inputOutput.showOutput(nameList.listNames(allContacts));
        int chosenContact = getValidDigit() - 1;
        allContacts.remove(chosenContact);
    }

    private int getValidDigit() {
        ValidDigit validDigit = new ValidDigit(inputOutput);
        return validDigit.getValidDigit();
    }
}
