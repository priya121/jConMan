package test.options;

import conMan.ConMan;
import conMan.ContactList;
import conMan.FileType;
import conMan.contactfields.Contact;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.Create;
import org.junit.Before;
import org.junit.Test;
import test.FakeExit;
import test.FakeFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    private ContactList imported;
    private ContactList contactList;
    private FakeExit exitOption;

    @Before
    public void setUp() throws IOException {
        getImportedContacts();
        contactList = new ContactList();
    }

    @Test
    public void createChoiceHasACreateTitle() throws IOException {
        InputOutput consoleIO = input("");
        Create createOption = new Create(imported, consoleIO);
        createOption.show();
        assertTrue(recordedOutput.toString().contains("Create a contact \n"));
    }

    @Test
    public void userEntering1ShowsCreateAContactTitle() {
        InputOutput consoleIO = input("1\n");
        FileType fakeFile = new FakeFile(consoleIO, contactList, imported);
        exitOption = new FakeExit(consoleIO, contactList, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuChoice();
        assertTrue(recordedOutput.toString().contains("Create a contact \n"));
    }

    @Test
    public void userAbleToCreateAContactByEnteringFields() throws IOException {
        InputOutput console = input("Maya\nPatil\n789@gmail.com\n2 Rosebury Av\n");
        Create create = new Create(contactList, console);
        create.perform();
        assertEquals("First Name: Maya\n" +
                     "Last Name: Patil\n" +
                     "Email: 789@gmail.com\n" +
                     "Home Address: 2 Rosebury Av\n\n\n", contactList.get(0).showFields());
    }

    @Test
    public void userCanCreateANewContactAfterEntering1() throws IOException {
        InputOutput consoleIO = input("1\nGary\nPaul\n345@gmail.com\n3 Rosebury Av\n2\nN\n3\n5\nY\n");
        FileType fakeFile = new FakeFile(consoleIO, contactList, imported);
        exitOption = new FakeExit(consoleIO, contactList, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, contactList);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("First Name: Gary\n" +
                     "Last Name: Paul\n" +
                     "Email: 345@gmail.com\n" +
                     "Home Address: 3 Rosebury Av\n\n\n"));
    }

    private Contact createContact(String input) {
        ConsoleIO console = new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
        return new Contact(console);
    }

    private void getImportedContacts() {
        imported = new ContactList();
        Contact ben = createContact("Ben\nSmith\n123@gmail.com\n1 Cedar Way\n");
        Contact sarah = createContact("Sarah\nSmith\n234@gmail.com\n2 Cedar Way\n");
        imported.addContact(ben);
        imported.addContact(sarah);
    }

    private InputOutput input(String input) {
        return new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
    }
}
