package conMan;

import conMan.contactfields.Contact;
import conMan.inputoutput.InputOutput;
import conMan.inputoutput.ValidDigit;
import conMan.options.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        this.file = file;
        this.console = console;
        this.allContacts = allContacts;
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
    private void importContacts() {
        JSONParser parser = new JSONParser();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            createContacts(parser, reader);
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private void createContacts(JSONParser parser, BufferedReader reader) throws IOException, ParseException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            JSONObject obj = (JSONObject) parser.parse(currentLine);
            String firstName = (String) obj.get("First Name: ");
            String lastName = (String) obj.get("Last Name: ");
            String email = (String) obj.get("Email: ");
            String homeAddress = (String) obj.get("Home Address: ");
            addContactsToList(firstName, lastName, email, homeAddress, console);
        }
    }

    private void addContactsToList(String firstName, String lastName, String email, String homeAddress, InputOutput console) {
        Contact contact = new Contact(firstName, lastName, email, homeAddress);
        contact.setExisting();
        allContacts.addContact(contact);
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
        importContacts();
        showGreeting();
        showOptionTitles();
        while (userChoice != 4) {
            optionSelected();
            showOptionTitles();
        }
    }
}
