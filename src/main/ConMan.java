package main;

import main.contactfields.Contact;
import main.inputoutput.InputOutput;
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
    private List<Option> options;

    public ConMan(InputOutput console){
        this.allContacts = new ArrayList<>();
        this.console = console;
        this.create = new Create(allContacts, console);
        this.read = new Read(allContacts, console);
        this.update = new Update(allContacts, console);
        this.delete = new Delete(allContacts, console);
        this.options = Arrays.asList(create, read, update, delete);
    }

    public String showGreeting() {
        return console.showOutput("Welcome to ConMan! \n" +
                "Please choose from the follwing options: \n" +
                "1) Create a contact \n" +
                "2) Read a contact \n" +
                "3) Update a contact \n" +
                "4) Delete a contact \n");
    }

    public void takeUserChoice() {
        int userChoice = Integer.parseInt(console.takeInput()) - 1;
        options.get(userChoice).show();
        options.get(userChoice).perform();
    }

    public void update() {
        update.perform();
    }

    public void create() {
        create.perform();
    }

    public String readContact(int contactNumber) {
        return read.contact(contactNumber);
    }

    public void delete() {
        delete.perform();
    }

    public String listAllNames() {
        NameList names = new NameList(allContacts);
        return names.listAllNames();
    }
}
