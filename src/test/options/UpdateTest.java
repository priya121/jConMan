package test.options;

import main.ConMan;
import main.contactfields.Contact;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import main.options.Update;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class UpdateTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    Contact maya = createContact(new ByteArrayInputStream("Maya\nPatil\n123@gmail.com\n".getBytes()));
    Contact sarah = createContact(new ByteArrayInputStream("Sarah\nSmith\n234@gmail.com\n".getBytes()));
    List<Contact> contacts = Arrays.asList(maya, sarah);

    @Test
    public void updateHasAUpdateTitle() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("3\n1\n".getBytes()), out);
        Update update = new Update(contacts, consoleIO);
        update.show();
        assertTrue(recordedOutput.toString().contains("Update a contact's details \n"));
    }

    @Test
    public void userEntering3ShowsUpdateAContactTitle() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nMaya\nPatil\n123@gmail.com\n" +
                                                                        "1\nSarah\nSmith\n234@gmail.com\n" +
                                                                        "3\n1\n").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO);
        createTwoContacts(conMan);
        conMan.optionSelected();
        assertTrue(recordedOutput.toString().contains("Update a contact's details \n"));
    }


    @Test
    public void userCanUpdateAChosenContactsDetails() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nMaya\nPatil\n123@gmail.com\n" +
                                                                        "1\nSarah\nSmith\n234@gmail.com\n" +
                                                                        "3\n1\nSam\nPatil\n789@gmail.com\n" +
                                                                        "2\n1\n").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO);
        createTwoContacts(conMan);
        conMan.optionSelected();
        conMan.optionSelected();
        assertTrue(recordedOutput.toString().contains("1) Sam Patil"));
    }

    private Contact createContact(InputStream inputStream) {
        ConsoleIO console = new ConsoleIO(inputStream, out);
        return new Contact(console);
    }

    private void createTwoContacts(ConMan conMan) {
        conMan.optionSelected();
        conMan.optionSelected();
    }
}
