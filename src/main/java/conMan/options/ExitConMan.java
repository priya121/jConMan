package conMan.options;

import conMan.ContactList;
import conMan.FileType;
import conMan.inputoutput.InputOutput;

public class ExitConMan implements Option, Exit {
    private final ContactList contactList;
    private final FileType file;
    private InputOutput console;

    public ExitConMan(InputOutput console, ContactList contactList, FileType file) {
        this.contactList = contactList;
        this.console = console;
        this.file = file;
    }

    @Override
    public void show() {
        console.showOutput("ExitConMan ConMan \n");
    }

    @Override
    public void perform() {
        file.saveContacts();
        System.exit(0);
    }
}
