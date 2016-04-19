package test;

import conMan.inputoutput.InputOutput;
import conMan.options.Exit;
import conMan.options.Option;
import fileTypes.FileType;

public class FakeExit implements Option, Exit {
    private final FileType file;
    private InputOutput inputOutput;


    public FakeExit(InputOutput inputOutput, FileType file) {
        this.inputOutput = inputOutput;
        this.file = file;
    }

    @Override
    public void show() {
        inputOutput.showOutput("ExitConMan conMan.ConMan \n");
    }

    @Override
    public void perform() {
        saveContacts();
        inputOutput.showOutput("Exiting ConMan...");
    }

    @Override
    public boolean contactsNecessary() {
        return false;
    }

    @Override
    public boolean contactsExist() {
        return false;
    }

    public void saveContacts() {
        file.saveContacts();
    }
}
