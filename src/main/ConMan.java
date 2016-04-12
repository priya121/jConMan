package main;

import main.contactfields.Contact;
import main.inputoutput.InputOutput;
import main.inputoutput.ValidDigit;
import main.options.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConMan {
    private ArrayList<Contact> allContacts;
    private InputOutput console;
    private Update update;
    private Create create;
    private Read read;
    private Delete delete;
    private ExitConMan exit;
    private List<Option> options;
    private int userChoice;

    public ConMan(InputOutput console, Option exit) {
        this.allContacts = new ArrayList<>();
        this.console = console;
        this.create = new Create(allContacts, console);
        this.read = new Read(allContacts, console);
        this.update = new Update(allContacts, console);
        this.delete = new Delete(allContacts, console);
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
        getValidDigit();
        options.get(userChoice).show();
        options.get(userChoice).perform();
    }

    private void getValidDigit() {
        ValidDigit validDigit = new ValidDigit();
        String userInput = console.takeInput();
        while (!validDigit.check(userInput)) {
            console.showOutput("Please enter a valid number: ");
            userInput = console.takeInput();
        }
        userChoice = Integer.parseInt(userInput) - 1;
    }

    public String readContact(int contactNumber) {
        return read.contact(contactNumber);
    }

    public String listAllNames() {
        NameList names = new NameList(allContacts);
        return names.listAllNames();
    }
}
