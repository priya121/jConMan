package test.options;

import conMan.ConMan;
import conMan.ContactList;
import conMan.contactfields.Contact;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.Create;
import conMan.options.Option;
import org.junit.Before;
import org.junit.Test;
import test.FakeExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateTest {
    private ContactList contactList;
    private Contact Ben;
    private Contact Sarah;

    @Before
    public void setUp() {
        contactList = new ContactList();
        Ben = createContact("Ben\nSmith\n123@gmail.com\n1 Cedar Way\n");
        Sarah = createContact("Sarah\nSmith\n234@gmail.com\n2 Cedar Way\n");
        contactList.addContact(Ben);
        contactList.addContact(Sarah);
    }

    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    @Test
    public void createChoiceHasACreateTitle() {
        InputOutput consoleIO = input("");
        Create createOption = new Create(contactList, consoleIO);
        createOption.show();
        assertTrue(recordedOutput.toString().contains("Create a contact \n"));
    }

    @Test
    public void userEntering1ShowsCreateAContactTitle() {
        InputOutput consoleIO = input("1\n");
        Option exitOption = new FakeExit(consoleIO);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.optionSelected();
        assertTrue(recordedOutput.toString().contains("Create a contact \n"));
    }

    @Test
    public void userAbleToCreateAContactByEnteringFields() {
        InputOutput console = input("Maya\nPatil\n789@gmail.com\n2 Rosebury Av\n");
        Create create = new Create(contactList, console);
        create.perform();
        assertEquals("First Name: Maya\n" +
                "Last Name: Patil\n" +
                "Email: 789@gmail.com\n" +
                "Home Address: 2 Rosebury Av\n", contactList.get(2).showFields());
    }

    @Test
    public void userCanCreateAContactAfterEntering1() {
        InputOutput consoleIO = input("1\nGary\nPaul\n345@gmail.com\n3 Rosebury Av\n");
        Option exitOption = new FakeExit(consoleIO);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.optionSelected();
        assertEquals("First Name: Gary\n" +
                "Last Name: Paul\n" +
                "Email: 345@gmail.com\n" +
                "Home Address: 3 Rosebury Av\n", conMan.readContact(1));
    }

    private Contact createContact(String input) {
        ConsoleIO console = new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
        return new Contact(console);
    }

    private InputOutput input(String input) {
        return new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
    }
}
