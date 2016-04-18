package test.options;

import conMan.ConMan;
import conMan.ContactList;
import conMan.FileType;
import conMan.contactfields.Contact;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.Option;
import conMan.options.Update;
import org.junit.Before;
import org.junit.Test;
import test.FakeExit;
import test.FakeFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdateTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    ContactList contactList;

    @Before
    public void setUp() throws IOException {
        contactList = new ContactList();
    }

    @Test
    public void updateHasAUpdateTitle() {
        Update update = new Update(contactList, input("3\n1\n"));
        update.show();
        assertTrue(recordedOutput.toString().contains("Update a contact's details \n"));
    }

    @Test
    public void userEntering3ShowsUpdateAContactTitle() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n123\n" +
                                      "3\n1\nSarah\nSmith\n234@gmail.com\n2 Cedar Way\n123\n" +
                                      "5\n");
        FileType fakeFile = new FakeFile(consoleIO, contactList, contactList);
        Option exitOption = new FakeExit(consoleIO, contactList, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Update a contact's details \n"));
    }

    @Test
    public void userCanUpdateAChosenContactsDetails() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n123\n" +
                                      "3\n1\nSam\nPatil\n789@gmail.com\n3 Cedar Way\n123\n" +
                                      "2\nN\n1\n5\n");
        FileType fakeFile = new FakeFile(consoleIO, contactList, contactList);
        Option exitOption = new FakeExit(consoleIO, contactList, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("First Name: Sam\n" +
                                                      "Last Name: Patil\n" +
                                                      "Email: 789@gmail.com\n" +
                                                      "Home Address: 3 Cedar Way\n"));
    }

    @Test
    public void userMustEnterAValidNumberWhenChoosingAContact() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n123\n" +
                                      "3\nabc\n1\nSam\nPatil\n789@gmail.com\n3 Cedar Way\n\n5\n");
        FileType fakeFile = new FakeFile(consoleIO, contactList, contactList);
        Option exitOption = new FakeExit(consoleIO, contactList, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Please enter a valid number: "));
    }

    @Test
    public void userCanReadListOfContactsBeforeDecidingWhichToUpdate() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n123\n" +
                                      "1\nMaya\nPatil\n345@gmail.com\n2 Cedar Way\n123\n" +
                                      "3\n1\nBen\nSmith\n123@gmail.com\n3 Cedar Way\n\n" +
                                      "5\n");
        FileType fakeFile = new FakeFile(consoleIO, contactList, contactList);
        Option exitOption = new FakeExit(consoleIO, contactList, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("1) Priya Patil\n" +
                                                      "2) Maya Patil\n"));
    }
    
    @Test
    public void userPromptThatUserCanLeaveFieldBlank() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n123\n" +
                                      "3\n1\nSarah\nSmith\n234@gmail.com\n2 Cedar Way\n123\n" +
                                      "5\n");
        FileType fakeFile = new FakeFile(consoleIO, contactList, contactList);
        Option exitOption = new FakeExit(consoleIO, contactList, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Fill in field to update or leave blank to keep previous value: \n"));
    }

    @Test
    public void ifUserLeavesFieldBlankPreviousValueRemains() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n123\n" +
                                      "3\n1\n\n\n\n3 Cedar Way\n\n" +
                                      "2\nN\n1\n" +
                                      "5\n");
        FileType fakeFile = new FakeFile(consoleIO, contactList, contactList);
        Option exitOption = new FakeExit(consoleIO, contactList, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("First Name: Priya\n" +
                                                      "Last Name: Patil\n" +
                                                      "Email: 123@gmail.com\n" +
                                                      "Home Address: 3 Cedar Way\n" +
                                                      "Phone Number: 123\n\n\n"));
    }

    @Test
    public void canUpdateAContactImportedFromAFile() {
        ContactList importedContact = new ContactList();
        InputOutput console = new ConsoleIO(new ByteArrayInputStream(("Maya\nPatil\n123@gmail.com\n5 Rosebury Av\n123\n" +
                                                                      "3\n1\nPriya\n\n\n3 Cedar Way\n\n" +
                                                                      "2\nN\n1\n2\nN\n1\n5\nY\n").getBytes()), out);
        createImportedContact(importedContact, console);
        FileType fakeFile = new FakeFile(console, contactList, importedContact);
        Option exitOption = new FakeExit(console, contactList, fakeFile);
        ConMan conMan = new ConMan(console, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertEquals("First Name: Priya\n" +
                "Last Name: Patil\n" +
                "Email: 123@gmail.com\n" +
                "Home Address: 3 Cedar Way\n" +
                "Phone Number: 123\n\n\n", contactList.get(0).showFields());
    }

    private void createImportedContact(ContactList importedContact, InputOutput console) {
        Contact maya = new Contact(console);
        maya.setFields();
        importedContact.addContact(maya);
    }

    private InputOutput input(String input) {
        return new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
    }
}
