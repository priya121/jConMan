package conMan;

import conMan.inputoutput.InputOutput;
import conMan.inputoutput.ValidDigit;
import conMan.options.*;
import fileTypes.FileType;

import java.util.Arrays;
import java.util.List;

public class ConMan {
    private ContactList allContacts;
    private InputOutput console;
    private List<Option> options;
    private int userChoice;
    private FileType file;

    public ConMan(InputOutput console, Option exit, FileType file, ContactList allContacts) {
        this.file = file;
        this.console = console;
        this.allContacts = allContacts;
        Create create = new Create(this.allContacts, console);
        Read read = new Read(this.allContacts, console);
        Update update = new Update(this.allContacts, console);
        Delete delete = new Delete(this.allContacts, console);
        this.options = Arrays.asList(create, read, update, delete, exit);
    }

    public void showGreeting() {
        console.showOutput("Welcome to ConMan! \n" +
                "Please choose from the following options: \n");
    }
    private void importContacts() {
        file.importContacts();
    }

    public void showOptionTitles() {
        int number = 1;
        for (Option option : options) {
            console.showOutput(String.valueOf(number) + ") ");
            option.show();
            number++;
        }
    }

    public void menuChoice() {
        userChoice = getValidDigit() - 1;
        console.clearScreen();
        options.get(userChoice).show();
        options.get(userChoice).perform();
    }

    private int getValidDigit() {
        ValidDigit validDigit = new ValidDigit(console);
        return validDigit.getValidDigit();
    }

    public String listAllNames() {
        NameList names = new NameList(allContacts, console);
        return names.formatNames(allContacts.getList());
    }

    public void menuLoop() {
        importContacts();
        showGreeting();
        showOptionTitles();
        while (userChoice != 4) {
            menuChoice();
            showOptionTitles();
        }
    }
}
