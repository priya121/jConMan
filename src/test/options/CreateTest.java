package test.options;

import main.ConMan;
import main.contactfields.Contact;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import main.options.Create;
import main.options.Option;
import org.junit.Test;
import test.FakeExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateTest {
    Contact Ben = createContact("Ben\nSmith\n123@gmail.com\n1 Cedar Way\n");
    Contact Sarah = createContact("Sarah\nSmith\n234@gmail.com\n2 Cedar Way\n");
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);


    @Test
    public void createChoiceHasACreateTitle() {
        InputOutput consoleIO = input("");
        List<Contact> contacts = Arrays.asList(Ben, Sarah);
        Create createOption = new Create(contacts, consoleIO);
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
        List<Contact> contacts = new ArrayList<>();
        Create create = new Create(contacts, console);
        create.perform();
        assertEquals("First Name: Maya\n" +
                     "Last Name: Patil\n" +
                     "Email: 789@gmail.com\n" +
                     "Home Address: 2 Rosebury Av\n", contacts.get(0).showFields());
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
