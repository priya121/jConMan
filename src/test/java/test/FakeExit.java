package test;

import conMan.inputoutput.InputOutput;
import conMan.options.Exit;
import conMan.options.Option;
import filetypes.FileType;

public class FakeExit implements Option, Exit {
    private final FileType file;
    private InputOutput console;


    public FakeExit(InputOutput console, FileType file) {
        this.console = console;
        this.file = file;
    }

    @Override
    public void show() {
        console.showOutput("ExitConMan conMan.ConMan \n");
    }

    @Override
    public void perform() {
        saveContacts();
        console.showOutput("Exiting ConMan...");
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
