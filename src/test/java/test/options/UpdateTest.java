package test.options;

import conMan.ConMan;
import conMan.ContactList;
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
    ContactList imported;
    FakeFile fakeFile;

    @Before
    public void setUp() throws IOException {
        contactList = new ContactList();
        imported = new ContactList();
        fakeFile = new FakeFile(contactList, imported);
    }

    @Test
    public void updateHasAUpdateTitle() {
        Update update = new Update(contactList, input("3\n1\n"));
        update.show();
        assertTrue(recordedOutput.toString().contains("Update a contact's details \n"));
    }

    @Test
    public void userEntering3ShowsUpdateAContactTitle() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n123\nwww\n\n" +
                                      "3\nN\n1\nSarah\nSmith\n234@gmail.com\n2 Cedar Way\n123\nwww\n\n" +
                                      "5\n");
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Update a contact's details \n"));
    }

    @Test
    public void userCanUpdateAChosenContactsDetails() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n123\nwww\n\n" +
                                      "3\nN\n1\nSam\nPatil\n789@gmail.com\n3 Cedar Way\n123\nwww\n\n" +
                                      "2\nN\n1\n\n5\n");
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("First Name: Sam\n" +
                                                      "Last Name: Patil\n" +
                                                      "Email: 789@gmail.com\n" +
                                                      "Home Address: 3 Cedar Way\n"));
    }

    @Test
    public void userMustEnterAValidNumberWhenChoosingAContact() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n123\nwww\n\n" +
                                      "3\nN\nabc\n1\nSam\nPatil\n789@gmail.com\n3 Cedar Way\n\nwww\n\n5\n");
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Please enter a valid number: "));
    }

    @Test
    public void userCanReadListOfContactsBeforeDecidingWhichToUpdate() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n123\nwww\n\n" +
                                      "1\nMaya\nPatil\n345@gmail.com\n2 Cedar Way\n123\nwww\n\n" +
                                      "3\nN\n1\nBen\nSmith\n123@gmail.com\n3 Cedar Way\n\nwww\n\n" +
                                      "5\n");
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("1) Priya Patil\n" +
                                                      "2) Maya Patil\n"));
    }
    
    @Test
    public void userPromptThatUserCanLeaveFieldBlank() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n123\nwww\n\n" +
                                      "3\nN\n1\nSarah\nSmith\n234@gmail.com\n2 Cedar Way\n123\nwww\n\n" +
                                      "5\n");
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Fill in field to update or leave blank to keep previous value: \n"));
    }

    @Test
    public void ifUserLeavesFieldBlankPreviousValueRemains() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n123\nwww\n\n" +
                                      "3\nN\n1\n\n\n\n3 Cedar Way\n\n\n\n" +
                                      "2\nN\n1\n\n" +
                                      "5\n");
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("First Name: Priya\n" +
                                                      "Last Name: Patil\n" +
                                                      "Email: 123@gmail.com\n" +
                                                      "Home Address: 3 Cedar Way\n" +
                                                      "Phone Number: 123\n" +
                                                      "Website: www\n\n\n"));
    }

    @Test
    public void displaysPreviousFieldValuesToUser() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n123\nwww\n\n" +
                                      "3\nN\n1\n\n\n\n3 Cedar Way\n\n\n\n" +
                                      "2\nN\n1\n\n" +
                                      "5\n");
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("First Name: (previous) Priya\n" +
                                                      "Last Name: (previous) Patil\n" +
                                                      "Email: (previous) 123@gmail.com\n" +
                                                      "Home Address: (previous) 1 Cedar Way\n" +
                                                      "Phone Number: (previous) 123\n" +
                                                      "Website: (previous) www\n"));
    }

    @Test
    public void canUpdateAContactImportedFromAFile() {
        InputOutput console = input("Maya\nPatil\n123@gmail.com\n5 Rosebury Av\n123\nwww\n\n" +
                                    "3\nN\n1\nPriya\n\n\n3 Cedar Way\n\n\n\n" +
                                    "2\nN\n1\n\n5\nY\n");
        createImportedContact(console);
        Option exitOption = new FakeExit(console, fakeFile);
        ConMan conMan = new ConMan(console, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertEquals("First Name: Priya\n" +
                     "Last Name: Patil\n" +
                     "Email: 123@gmail.com\n" +
                     "Home Address: 3 Cedar Way\n" +
                     "Phone Number: 123\n" +
                     "Website: www\n\n\n", contactList.getContact(0).showFields());
    }

    @Test
    public void asksUserToHitEnterKeyToGoBackToMainMenuAfterUpdatingContact() {
        InputOutput consoleIO = input("3\nN\n1\n\n\n\n\n\n\n\n" +
                                      "5\nY\n");
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Hit any key to go back to the main menu." +
                                                      "\033[2J\033[1;1H"));
    }

    @Test
    public void usercanFilterContactsBeforeChoosingToUpdte() {
        InputOutput consoleIO = input("3\nN\n1\n\n\n\n\n\n\n\n" +
                                      "5\nY\n");
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Hit any key to go back to the main menu." +
                "\033[2J\033[1;1H"));
    }

    @Test
    public void DoesNotAllowUserToUpdateIfNoContactsExist() {
        InputOutput consoleIO = input("3\nN\n\n5\n");
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("There are no contacts to update.\n\n"));
    }

    private void createImportedContact(InputOutput console) {
        Contact maya = new Contact(console);
        maya.setFields();
        imported.addContact(maya);
    }

    private InputOutput input(String input) {
        return new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
    }
}
