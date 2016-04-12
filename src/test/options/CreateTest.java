package test.options;

import main.ConMan;
import main.contactfields.Contact;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import main.options.Create;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateTest {
    Contact Ben = createContact(new ByteArrayInputStream("Ben\nSmith\n123@gmail.com\n".getBytes()));
    Contact Sarah = createContact(new ByteArrayInputStream("Sarah\nSmith\n234@gmail.com\n".getBytes()));
    List<Contact> contacts = Arrays.asList(Ben, Sarah);
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);


    @Test
    public void createChoiceHasACreateTitle() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("".getBytes()), out);
        Create createOption = new Create(contacts, consoleIO);
        createOption.show();
        assertTrue(recordedOutput.toString().contains("Create a contact: \n"));
    }

    @Test
    public void userEntering1ShowsCreateAContactTitle() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("1\n".getBytes()), out);
        ConMan conMan = new ConMan(consoleIO);
        conMan.optionSelected();
        assertTrue(recordedOutput.toString().contains("Create a contact: \n"));
    }

    @Test
    public void userAbleToCreateAContactByEnteringFields() {
        ConsoleIO console = new ConsoleIO(new ByteArrayInputStream(("Maya\nPatil\n789@gmail.com\n").getBytes()), out);
        List<Contact> contacts = new ArrayList<>();
        Create create = new Create(contacts, console);
        create.perform();
        assertEquals("First Name: Maya\n" +
                     "Last Name: Patil\n" +
                     "Email: 789@gmail.com\n", contacts.get(0).showFields());
    }

    @Test
    public void userCanCreateAContactAfterEntering1() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("1\nGary\nPaul\n345@gmail.com\n".getBytes()), out);
        ConMan conMan = new ConMan(consoleIO);
        conMan.optionSelected();
        assertEquals("First Name: Gary\n" +
                     "Last Name: Paul\n" +
                     "Email: 345@gmail.com\n", conMan.readContact(1));
    }

    private Contact createContact(InputStream inputStream) {
        ConsoleIO console = new ConsoleIO(inputStream, out);
        return new Contact(console);
    }
}
