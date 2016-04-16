package conMan;

import conMan.inputoutput.InputOutput;
import conMan.inputoutput.ValidDigit;
import conMan.options.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ConMan {
    private ContactList allContacts;
    private InputOutput console;
    private Update update;
    private Create create;
    private Read read;
    private Delete delete;
    private ExitConMan exit;
    private List<Option> options;
    private int userChoice;
    private File file;

    public ConMan(InputOutput console, Option exit, File file, ContactList allContacts) {
        this.allContacts = allContacts;
        this.file = file;
        this.console = console;
        this.create = new Create(this.allContacts, console);
        this.read = new Read(this.allContacts, console);
        this.update = new Update(this.allContacts, console);
        this.delete = new Delete(this.allContacts, console);
        this.options = Arrays.asList(create, read, update, delete, exit);
    }

    public void showGreeting() {
        console.showOutput("Welcome to ConMan! \n" +
                "Please choose from the following options: \n");
    }

    public void showOptionTitles() {
        int number = 1;
        for (Option option : options) {
            console.showOutput(String.valueOf(number) + ") ");
            option.show();
            number++;
        }
    }

    public void optionSelected() {
        userChoice = getValidDigit() - 1;
        options.get(userChoice).show();
        options.get(userChoice).perform();
    }

    private int getValidDigit() {
        ValidDigit validDigit = new ValidDigit(console);
        return validDigit.getValidDigit();
    }

    public String readContact(int contactNumber) {
        return read.contact(contactNumber);
    }

    public String listAllNames() {
        NameList names = new NameList(allContacts, console);
        return names.listNames(allContacts.getList());
    }

    public void menuLoop() {
        showGreeting();
        showOptionTitles();
        while (userChoice != 4) {
            optionSelected();
            showOptionTitles();
        }
    }


}
