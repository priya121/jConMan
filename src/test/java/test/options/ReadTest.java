package test.options;

import conMan.ConMan;
import conMan.ContactList;
import conMan.contactfields.Contact;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.Read;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import test.FakeExit;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class ReadTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    File output;
    FakeExit exitOption;
    ContactList contactList = new ContactList();
    InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                  "2\n1\n5\n");

    @Before
    public void setUp() throws IOException {
        output = temporaryFolder.newFile("output.txt");
        exitOption = new FakeExit(consoleIO, contactList, output);
    }

    @Test
    public void readHasAReadTitle() {
        ContactList list = new ContactList();
        Read read = new Read(list, consoleIO);
        read.show();
        assertTrue(recordedOutput.toString().contains("Read a contact's details \n"));
    }

    @Test
    public void userEntering2ShowsReadAContactsDetailsTitle() {
        ConMan conMan = new ConMan(consoleIO, exitOption, output, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Read a contact's details \n"));
    }

    @Test
    public void userEntering1ReadsFirstContact() {
        addContactsToList();
        Read read = new Read(contactList, consoleIO);
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
        ConMan conMan = new ConMan(consoleIO, exitOption, output, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("1) Priya Patil\n" +
                                                      "2) Sarah Black\n"));
    }

    @Test
    public void userMustEnterAValidNumberToReadContactAfterEnteringReadOption() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n2 Cedar Way\n2\na\n1\n5\n");
        ConMan conMan = new ConMan(consoleIO, exitOption, output, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Please enter a valid number: "));
    }

    private void addContactsToList() {
        Contact priya = createContact("Priya\nPatil\n123@gmail.com\n1 Cedar Way\n");
        Contact sarah = createContact("Sarah\nBlack\n234@gmail.com\n2 Cedar Way\n" +
                                      "2\n1\n");
        contactList.addContact(priya);
        contactList.addContact(sarah);
        setFields(contactList);
    }

    private void setFields(ContactList list) {
        for (Contact contact : list.getList()) {
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
