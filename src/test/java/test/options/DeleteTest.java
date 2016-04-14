package test.options;

import conMan.ConMan;
import conMan.ContactList;
import conMan.contactfields.Contact;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.Delete;
import org.junit.Before;
import org.junit.Test;
import test.FakeExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeleteTest {

    private Contact ben;
    private Contact priya;
    private InputOutput consoleIO;
    private FakeExit exitOption;
    private ConMan conMan;
    private ContactList contactList;

    @Before
    public void setUp() {
        ben = createContact("Ben\nSmith\n234@gmail.com\n2 Rosebury Av\n");
        priya = createContact("Priya\nPatil\n123@gmail.com\n3 Rosebury Av\n");
        consoleIO = input("1\nBen\nSmith\n345@gmail.com\n2 Rosebury Av\n4\n1\n5\n");
        exitOption = new FakeExit(consoleIO);
        conMan = new ConMan(consoleIO, exitOption);
        contactList = new ContactList();
    }

    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    @Test
    public void deleteHasADeleteTitle() {
        Delete delete = new Delete(contactList, consoleIO);
        delete.show();
        assertTrue(recordedOutput.toString().contains("Delete a contact "));
    }

    @Test
    public void userEntering4ShowsDeleteAContactTitle() {
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Delete a contact "));
    }

    @Test
    public void userAbleToDeleteAContact() {
        InputOutput console = input("Ben\nSmith\n123@gmail.com\n1 Cedar Way\n" +
                                  "Sarah\nSmith\n678@gmail.com\n2 Cedar Way\n1\n");
        ContactList contacts = createContactList(console);
        Delete delete = new Delete(contactList, console);
        delete.perform();
        assertEquals("Sarah Smith", contacts.get(0).getName());
    }

    @Test
    public void userMustEnterAValidNumberToDeleteAContact() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n2 Cedar Way\n" +
                                      "4\na\n1\n5\n");
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Please enter a valid number: "));
    }

    @Test
    public void userCanReadListOfContactsBeforeDecidingWhichToDelete() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                      "1\nMaya\nPatil\n345@gmail.com\n2 Cedar Way\n" +
                                      "1\nBen\nSmith\n123@gmail.com\n3 Cedar Way\n" +
                                      "4\n1\n5\n");
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("1) Priya Patil\n" +
                                                      "2) Maya Patil\n" +
                                                      "3) Ben Smith\n"));
    }

    private InputOutput input(String input) {
        return new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
    }

    private Contact createContact(String input) {
        ConsoleIO console = new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
        return new Contact(console);
    }

    private void setFields(ContactList contacts) {
        for (Contact contact : contacts.getList()) {
            contact.setFields();
        }
    }

    private ContactList createContactList(InputOutput console) {
        Contact ben = new Contact(console);
        Contact sarah = new Contact(console);
        contactList.addContact(ben);
        contactList.addContact(sarah);
        setFields(contactList);
        return contactList;
    }
}
