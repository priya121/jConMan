package test;

import conMan.ContactList;
import conMan.FileType;
import conMan.inputoutput.InputOutput;
import conMan.options.Exit;
import conMan.options.Option;

public class FakeExit implements Option, Exit {
    private final ContactList contactList;
    private final FileType file;
    private InputOutput inputOutput;


    public FakeExit(InputOutput inputOutput, ContactList contactList, FileType file) {
        this.inputOutput = inputOutput;
        this.contactList = contactList;
        this.file = file;
    }

    @Override
    public void show() {
        saveContacts();
        inputOutput.showOutput("ExitConMan conMan.ConMan \n");
    }

    @Override
    public void perform() {
        inputOutput.showOutput("Exiting ConMan...");
    }

    public void saveContacts() {
        file.saveContacts();
    }
}
