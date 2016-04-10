package main;

import main.inputoutput.InputOutput;
import main.options.Create;
import main.options.Delete;
import main.options.Read;
import main.options.Update;

import java.util.ArrayList;

public class ConMan {
    private ArrayList<Contact> allContacts;
    private final InputOutput console;
    private final Update update;
    private final Create create;
    private final Read read;
    private final Delete delete;

    public ConMan(InputOutput console){
        this.allContacts = new ArrayList<>();
        this.console = console;
        this.create = new Create(allContacts);
        this.read = new Read(allContacts);
        this.update = new Update(allContacts);
        this.delete = new Delete(allContacts);
    }

    public String showGreeting() {
        return console.showOutput("Welcome to ConMan!");
    }

    public void create(Contact contact) {
        create.add(contact);
    }

    public String readName(int contactNumber) {
        return read.name(contactNumber);
    }

    public String readContact(int contactNumber) {
        return read.contact(contactNumber);
    }

    public void updateFirstName(int contactNumber, String newName) {
        update.firstName(contactNumber, newName);
    }

    public void updateLastName(int contactNumber, String newName) {
        update.lastName(contactNumber, newName);
    }

    public void updateEmail(int contactNumber, String newEmail) {
        update.email(contactNumber, newEmail);
    }

    public void delete(int contactNumber) {
        delete.delete(contactNumber - 1);
    }

    public String listAllNames() {
        String listOfContacts = "";
        int number = 1;
        for (Contact contact : allContacts) {
            listOfContacts += number + ") " + contact.getName() + "\n";
            number++;
        }
        return listOfContacts;
    }

}
