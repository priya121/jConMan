package test.options;

import main.ConMan;
import main.contactfields.Contact;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import main.options.Option;
import main.options.Read;
import org.junit.Test;
import test.FakeExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ReadTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    List<Contact> contacts = new ArrayList<>();
    InputOutput consoleIO =  input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                    "2\n1\n5\n");
    Option exitOption = new FakeExit(consoleIO);

    @Test
    public void readHasAReadTitle() {
        Read read = new Read(contacts, consoleIO);
        read.show();
        assertTrue(recordedOutput.toString().contains("Read a contact's details \n"));
    }

    @Test
    public void userEntering2ShowsReadAContactsDetailsTitle() {
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Read a contact's details \n"));
    }

    @Test
    public void userEntering1ReadsFirstContact() {
        addContactsToList();
        Read read = new Read(contacts, consoleIO);
        read.perform();
        assertTrue(recordedOutput.toString().contains("First Name: Priya\n" +
                                                      "Last Name: Patil\n" +
                                                      "Email: 123@gmail.com\n" +
                                                      "Home Address: 1 Cedar Way\n"));
    }

    @Test
    public void readFirstDisplaysAListOfNames() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n2 Cedar Way\n" +
                                      "1\nSarah\nBlack\n345@gmail.com\n 3 Cedar Way\n" +
                                      "2\n1\n5\n");
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("1) Priya Patil\n" +
                                                      "2) Sarah Black\n"));
    }

    @Test
    public void userMustEnterAValidNumberToReadContactAfterEnteringReadOption() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n2 Cedar Way\n2\na\n1\n5\n");
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Please enter a valid number: "));
    }


    private void addContactsToList() {
        Contact priya = createContact("Priya\nPatil\n123@gmail.com\n1 Cedar Way\n");
        Contact sarah = createContact("Sarah\nBlack\n234@gmail.com\n2 Cedar Way\n" +
                                      "2\n1\n");
        contacts.add(priya);
        contacts.add(sarah);
        setFields(contacts);
    }

    private void setFields(List<Contact> contacts) {
        for (Contact contact : contacts) {
            contact.setFields();
        }
    }

    private Contact createContact(String input) {
        ConsoleIO console = new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
        return new Contact(console);
    }

    private InputOutput input(String input) {
        return new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
    }
}
