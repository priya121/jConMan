package test.options;

import conMan.ConMan;
import conMan.ContactList;
import conMan.FileType;
import conMan.contactfields.Contact;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.Delete;
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

public class DeleteTest {
    private FakeExit exitOption;
    private ConMan conMan;
    private ContactList contactList;
    private ContactList allContacts;
    private ContactList newList;
    private InputOutput console;

    @Before
    public void setUp() throws IOException {
        allContacts = new ContactList();
        console = input("Ben\nSmith\n123@gmail.com\n1 Cedar Way\n123\n" +
                        "Sarah\nSmith\n678@gmail.com\n2 Cedar Way\n123\n4\n1\nY\n5\nY\n");
        newList = new ContactList();
        contactList = createContactList(console);
    }

    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    @Test
    public void deleteHasADeleteTitle() {
        InputOutput consoleIO = input("1\nBen\nSmith\n345@gmail.com\n2 Rosebury Av\n4\n1\nY\n5\n");
        Delete delete = new Delete(contactList, consoleIO);
        delete.show();
        assertTrue(recordedOutput.toString().contains("Delete a contact "));
    }

    @Test
    public void userEntering4ShowsDeleteAContactTitle() {
        InputOutput consoleIO = input("1\nBen\nSmith\n345@gmail.com\n2 Rosebury Av\n123\n4\n1\nY\n5\n");
        FileType fakeFile = new FakeFile(consoleIO, allContacts, contactList);
        exitOption = new FakeExit(consoleIO, allContacts, fakeFile);
        conMan = new ConMan(consoleIO, exitOption, fakeFile, allContacts);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Delete a contact "));
    }

    @Test
    public void userAbleToDeleteFirstContact() {
        InputOutput console = input("Ben\nSmith\n123@gmail.com\n1 Cedar Way\n123\n" +
                                    "Sarah\nSmith\n678@gmail.com\n2 Cedar Way\n123\n1\nY\n5\nY\n");
        contactList = createContactList(console);
        Delete delete = new Delete(contactList, console);
        delete.perform();
        assertEquals("Sarah Smith", contactList.get(0).getName());
    }

    @Test
    public void userMustEnterAValidNumberToDeleteAContact() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n2 Cedar Way\n123\n" +
                "4\na\n1\nY\n5\n");
        FileType fakeFile = new FakeFile(consoleIO, allContacts, contactList);
        exitOption = new FakeExit(consoleIO, allContacts, fakeFile);
        conMan = new ConMan(consoleIO, exitOption, fakeFile, allContacts);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Please enter a valid number: "));
    }

    @Test
    public void userCanReadListOfContactsBeforeDecidingWhichToDelete() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n123\n" +
                                      "1\nMaya\nPatil\n345@gmail.com\n2 Cedar Way\n123\n" +
                                      "4\n1\nY\n5\n");
        FileType fakeFile = new FakeFile(consoleIO, allContacts, contactList);
        exitOption = new FakeExit(consoleIO, allContacts, fakeFile);
        conMan = new ConMan(consoleIO, exitOption, fakeFile, allContacts);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("1) Ben Smith\n" +
                                                      "2) Sarah Smith\n" +
                                                      "3) Priya Patil\n" +
                                                      "4) Maya Patil\n"));
    }

    @Test
    public void userAskedToConfirmBeforeDeletingAContact() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n123\n" +
                                      "4\n1\nY\n5\n");
        FileType fakeFile = new FakeFile(consoleIO, allContacts, contactList);
        exitOption = new FakeExit(consoleIO, allContacts, fakeFile);
        conMan = new ConMan(consoleIO, exitOption, fakeFile, allContacts);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Are you sure you want to delete this contact? (Y/N)"));
    }

    @Test
    public void doesNotDeleteAContactIfUserEntersN() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n123\n" +
                "4\n1\nN\n5\n");
        FileType fakeFile = new FakeFile(consoleIO, allContacts, contactList);
        exitOption = new FakeExit(consoleIO, allContacts, fakeFile);
        conMan = new ConMan(consoleIO, exitOption, fakeFile, allContacts);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Your contacts have not been changed"));
    }

    private InputOutput input(String input) {
        return new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
    }

    private void setFields(ContactList contacts) {
        for (Contact contact : contacts.getList()) {
            contact.setFields();
        }
    }

    private ContactList createContactList(InputOutput console) {
        newList = new ContactList();
        Contact ben = new Contact(console);
        Contact sarah = new Contact(console);
        newList.addContact(ben);
        newList.addContact(sarah);
        setFields(newList);
        return newList;
    }
}
