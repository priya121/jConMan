package test.options;

import conMan.ConMan;
import conMan.ContactList;
import conMan.contactfields.Contact;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.Option;
import conMan.options.Update;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import test.FakeExit;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class UpdateTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private File output;
    private ContactList contactList;

    @Before
    public void setUp() throws IOException {
         output = temporaryFolder.newFile("output.txt");
        contactList = new ContactList();
    }

    @Test
    public void updateHasAUpdateTitle() {
        Contact maya = createContact("Maya\nPatil\n123@gmail.com\n5 Rosebury Av\n");
        Contact sarah = createContact("Sarah\nSmith\n234@gmail.com\n6 Rosebury Av\n");
        ContactList list = new ContactList();
        list.addContact(maya);
        list.addContact(sarah);
        Update update = new Update(list, input("3\n1\n"));
        update.show();
        assertTrue(recordedOutput.toString().contains("Update a contact's details \n"));
    }

    @Test
    public void userEntering3ShowsUpdateAContactTitle() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                      "3\n1\nSarah\nSmith\n234@gmail.com\n2 Cedar Way\n" +
                                      "5\n");
        Option exitOption = new FakeExit(consoleIO, contactList, output);
        ConMan conMan = new ConMan(consoleIO, exitOption, output, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Update a contact's details \n"));
    }

    @Test
    public void userCanUpdateAChosenContactsDetails() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                      "3\n1\nSam\nPatil\n789@gmail.com\n3 Cedar Way\n" +
                                      "2\n1\n5\n");
        Option exitOption = new FakeExit(consoleIO, contactList, output);
        ConMan conMan = new ConMan(consoleIO, exitOption, output, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("First Name: Sam\n" +
                                                      "Last Name: Patil\n" +
                                                      "Email: 789@gmail.com\n" +
                                                      "Home Address: 3 Cedar Way\n"));
    }

    @Test
    public void userMustEnterAValidNumberWhenChoosingAContact() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                      "3\nabc\n1\nSam\nPatil\n789@gmail.com\n3 Cedar Way\n5\n");
        Option exitOption = new FakeExit(consoleIO, contactList, output);
        ConMan conMan = new ConMan(consoleIO, exitOption, output, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Please enter a valid number: "));
    }

    @Test
    public void userCanReadListOfContactsBeforeDecidingWhichToUpdate() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                      "1\nMaya\nPatil\n345@gmail.com\n2 Cedar Way\n" +
                                      "3\n1\nBen\nSmith\n123@gmail.com\n3 Cedar Way\n" +
                                      "5\n");
        Option exitOption = new FakeExit(consoleIO, contactList, output);
        ConMan conMan = new ConMan(consoleIO, exitOption, output, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("1) Priya Patil\n" +
                                                      "2) Maya Patil\n"));
    }
    
    @Test
    public void userPromptThatUserCanLeaveFieldBlank() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                      "3\n1\nSarah\nSmith\n234@gmail.com\n2 Cedar Way\n" +
                                      "5\n");
        Option exitOption = new FakeExit(consoleIO, contactList, output);
        ConMan conMan = new ConMan(consoleIO, exitOption, output, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Fill in field to update or leave blank to keep previous value: \n"));
    }

    @Test
    public void ifUserLeavesFieldBlankPreviousValueRemains() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                      "3\n1\n\n\n\n3 Cedar Way\n" +
                                      "2\n1\n" +
                                      "5\n");
        Option exitOption = new FakeExit(consoleIO, contactList, output);
        ConMan conMan = new ConMan(consoleIO, exitOption, output, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("First Name: Priya\n" +
                                                      "Last Name: Patil\n" +
                                                      "Email: 123@gmail.com\n" +
                                                      "Home Address: 3 Cedar Way\n"));
    }

    private Contact createContact(String input) {
        ConsoleIO console = new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
        return new Contact(console);
    }

    private InputOutput input(String input) {
        return new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
    }
}
