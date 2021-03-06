package test.options;

import conMan.ConMan;
import conMan.ContactList;
import conMan.contactfields.Contact;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.Delete;
import file.FakeFile;
import org.junit.Before;
import org.junit.Test;
import test.FakeExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeleteTest {
    private FakeExit exitOption;
    private ContactList contactList;
    private ContactList allContacts;
    private ContactList newList;
    private ContactList emptyList;
    private FakeFile fakeFile;

    @Before
    public void setUp() throws IOException {
        allContacts = new ContactList();
        InputOutput console = input("Ben\nSmith\n123@gmail.com\n1 Cedar Way\n04.05.06\n123\nwww\n" +
                                    "Sarah\nSmith\n678@gmail.com\n2 Cedar Way\n04.05.06\n123\nwww\n\n" +
                                    "4\n1\nY\n\n5\nY\n");
        newList = new ContactList();
        contactList = createContactList(console);
        emptyList = new ContactList();
        fakeFile = new FakeFile(allContacts, contactList);
    }

    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    @Test
    public void deleteHasADeleteTitle() {
        InputOutput consoleIO = input("1\nBen\nSmith\n345@gmail.com\n2 Rosebury Av\n4\n1\nY\n\n5\n");
        Delete delete = new Delete(contactList, consoleIO);
        delete.show();
        assertTrue(recordedOutput.toString().contains("Delete a contact "));
    }

    @Test
    public void userEntering4ShowsDeleteAContactTitle() {
        InputOutput consoleIO = input("1\nBen\nSmith\n345@gmail.com\n2 Rosebury Av\n04.05.06\n123\nwww\n\n" +
                                      "4\nN\n1\nY\n\n5\n");
        menuLoop(consoleIO);
        assertTrue(recordedOutput.toString().contains("Delete a contact "));
    }

    @Test
    public void userAbleToDeleteFirstContact() {
        InputOutput console = input("Ben\nSmith\n123@gmail.com\n1 Cedar Way\n04.05.06\n123\nwww\n" +
                                    "Sarah\nSmith\n678@gmail.com\n2 Cedar Way\n04.05.06\n123\nwww\n" +
                                    "4\nN\n1\nY\n5\n\nY\n");
        contactList = createContactList(console);
        Delete delete = new Delete(contactList, console);
        delete.perform();
        assertEquals("Sarah Smith", contactList.getContact(0).getName());
    }

    @Test
    public void userMustEnterAValidNumberToDeleteAContact() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n2 Cedar Way\n04.05.06\n123\nwww\n\n" +
                                      "4\nN\na\n1\nY\n\n5\n");
        menuLoop(consoleIO);
        assertTrue(recordedOutput.toString().contains("Please enter a valid number: "));
    }

    @Test
    public void userCanReadListOfContactsBeforeDecidingWhichToDelete() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n04.05.06\n123\nwww\n\n" +
                                      "1\nMaya\nPatil\n345@gmail.com\n2 Cedar Way\n04.05.06\n123\nwww\n\n" +
                                      "4\nN\n1\nY\n\n5\n");
        exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, allContacts);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("1) Ben Smith\n" +
                                                      "2) Sarah Smith\n" +
                                                      "3) Priya Patil\n" +
                                                      "4) Maya Patil\n"));
    }

    @Test
    public void userAskedToConfirmBeforeDeletingAContact() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n04.05.06\n123\nwww\n\n" +
                                      "4\nN\n1\nY\n\n5\n");
        exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, allContacts);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Are you sure you want to delete this contact? (Y/N)\n" +
                                                      "First Name: Ben\n" +
                                                      "Last Name: Smith\n" +
                                                      "Email: 123@gmail.com\n" +
                                                      "Home Address: 1 Cedar Way\n" +
                                                      "D.O.B: 04.05.06\n" +
                                                      "Phone Number: 123\n" +
                                                      "Website: www\n\n\n"));
    }

    @Test
    public void doesNotDeleteAContactIfUserEntersN() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n04.05.06\n123\nwww\n\n" +
                                      "4\nN\n1\nN\n\n5\n");
        menuLoop(consoleIO);
        assertTrue(recordedOutput.toString().contains("Your contacts have not been changed"));
    }

    @Test
    public void doesNotAllowUserToDeleteAContactIfNoContactsExist() {
        InputOutput consoleIO = input("4\n\n5\n");
        menuLoop(consoleIO);
        assertTrue(recordedOutput.toString().contains("There are no contacts to delete.\n\n"));
    }

    @Test
    public void asksUserToHitEnterKeyToGoBackToMainMenuAfterDeleteOptionSelected() {
        InputOutput consoleIO = input("4\n\n5\n");
        menuLoop(consoleIO);
        assertTrue(recordedOutput.toString().contains("Hit any key to go back to the main menu." +
                                                      "\033[2J\033[1;1H"));
    }

    private void menuLoop(InputOutput consoleIO) {
        exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, emptyList);
        conMan.menuLoop();
    }

    private InputOutput input(String input) {
        return new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
    }

    private void setFields(ContactList contacts) {
        for (Contact contact : contacts.get()) {
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
