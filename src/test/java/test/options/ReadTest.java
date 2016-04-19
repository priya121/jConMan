package test.options;

import conMan.ConMan;
import conMan.ContactList;
import conMan.contactfields.Contact;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.Option;
import conMan.options.Read;
import fileTypes.FileType;
import org.junit.Test;
import test.FakeExit;
import test.FakeFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class ReadTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    ContactList imported = new ContactList();
    ContactList contactList = new ContactList();
    InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n123\nwww\n\n" +
                                  "2\nN\n\n1\n\n5\n");
    FileType fakeFile = new FakeFile(contactList, imported);

    @Test
    public void readHasAReadTitle() {
        Read read = new Read(imported, consoleIO);
        read.show();
        assertTrue(recordedOutput.toString().contains("Read a contact's details \n"));
    }

    @Test
    public void userEntering2ShowsReadAContactsDetailsTitle() {
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Read a contact's details \n"));
    }

    @Test
    public void userEntering1ReadsFirstContact() {
        InputOutput consoleIO = input("2\nN\n1\n\n5\n");
        Read read = new Read(imported, consoleIO);
        addContactsToList();
        read.perform();
        assertTrue(recordedOutput.toString().contains("First Name: Priya\n" +
                                                      "Last Name: Patil\n" +
                                                      "Email: 123@gmail.com\n" +
                                                      "Home Address: 1 Cedar Way\n" +
                                                      "Phone Number: 123\n" +
                                                      "Website: www\n\n\n"));
    }

    @Test
    public void readFirstDisplaysAListOfNames() {
        InputOutput consoleIO = input("2\nN\n1\n\n5\n");
        addContactsToList();
        Option exitOption = new FakeExit(consoleIO,  fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("1) Priya Patil\n" +
                                                      "2) Sarah Black\n"));
    }

    @Test
    public void userMustEnterAValidNumberToReadContactAfterEnteringReadOption() {
        InputOutput consoleIO = input("2\nN\na\n1\n\n5\n");
        addContactsToList();
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Please enter a valid number: "));
    }

    @Test
    public void userCanFilterNamesBeforeSelecting() {
        InputOutput consoleIO = input("2\nY\nSarah\n1\n\n5\n");
        addContactsToList();
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Enter a name to filter: \n"));
    }

    @Test
    public void asksUserToEnterAnotherNumberIfContactNumberTooHigh() {
        InputOutput consoleIO = input("2\nY\nSarah\n8\n1\n\n5\n");
        addContactsToList();
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Please enter a valid number: "));
    }

    @Test
    public void tellsUserNoContactsToDisplayIfContactsDontExist() {
        InputOutput consoleIO = input("2\n\n5\n");
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("There are no contacts to display.\n"));
    }

    private void addContactsToList() {
        Contact priya = createContact("Priya\nPatil\n123@gmail.com\n1 Cedar Way\n123\nwww\n");
        Contact sarah = createContact("Sarah\nBlack\n234@gmail.com\n2 Cedar Way\n123\nwww\n" +
                                      "2\n1\n\n");
        imported.addContact(priya);
        imported.addContact(sarah);
        setFields(imported);
    }

    private void setFields(ContactList list) {
        for (Contact contact : list.get()) {
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
