package main.options;

import main.Contact;
import main.NameList;
import main.inputoutput.InputOutput;

import java.util.List;

public class Read implements Option {
    private final List<Contact> allContacts;
    private final InputOutput inputOutput;

    public Read(List<Contact> allContacts, InputOutput inputOutput) {
        this.allContacts = allContacts;
        this.inputOutput = inputOutput;
    }

    @Override
    public void show() {
        inputOutput.showOutput("Read a contact's details: \n");
    }

    @Override
    public void perform() {
        NameList names = new NameList(allContacts);
        inputOutput.showOutput("Select a contact to view: ");
        inputOutput.showOutput(names.listAllNames());
        inputOutput.showOutput(contact(Integer.parseInt(inputOutput.takeInput())));
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
