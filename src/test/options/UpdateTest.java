package test.options;

import main.ConMan;
import main.contactfields.Contact;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import main.options.Option;
import main.options.Update;
import org.junit.Test;
import test.FakeExit;

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
    Contact maya = createContact(new ByteArrayInputStream("Maya\nPatil\n123@gmail.com\n5 Rosebury Av\n".getBytes()));
    Contact sarah = createContact(new ByteArrayInputStream("Sarah\nSmith\n234@gmail.com\n6 Rosebury Av\n".getBytes()));
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
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                                                        "1\nSarah\nSmith\n234@gmail.com\n2 Cedar Way\n" +
                                                                        "3\n1\n").getBytes()), out);
        Option exitOption = new FakeExit(consoleIO);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        createTwoContacts(conMan);
        conMan.optionSelected();
        assertTrue(recordedOutput.toString().contains("Update a contact's details \n"));
    }

    @Test
    public void userCanUpdateAChosenContactsDetails() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                                                        "1\nSarah\nSmith\n234@gmail.com\n2 Cedar Way\n" +
                                                                        "3\n1\nSam\nPatil\n789@gmail.com\n3 Cedar Way\n" +
                                                                        "2\n1\n").getBytes()), out);
        Option exitOption = new FakeExit(consoleIO);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        createTwoContacts(conMan);
        conMan.optionSelected();
        conMan.optionSelected();
        assertTrue(recordedOutput.toString().contains("First Name: Sam\n" +
                                                      "Last Name: Patil\n" +
                                                      "Email: 789@gmail.com\n" +
                                                      "Home Address: 3 Cedar Way\n"));
    }

    @Test
    public void userMustEnterAValidNumberWhenChoosingAContact() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                                                        "3\nabc\n1\nSam\nPatil\n789@gmail.com\n3 Cedar Way\n").getBytes()), out);
        Option exitOption = new FakeExit(consoleIO);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.optionSelected();
        conMan.optionSelected();
        assertTrue(recordedOutput.toString().contains("Please enter a valid number: "));
    }

    @Test
    public void userCanReadListOfContactsBeforeDecidingWhichToUpdate() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                                                        "1\nMaya\nPatil\n345@gmail.com\n2 Cedar Way\n" +
                                                                        "3\n1\nBen\nSmith\n123@gmail.com\n3 Cedar Way\n" +
                                                                        "5\n").getBytes()), out);
        Option exitOption = new FakeExit(consoleIO);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("1) Priya Patil\n" +
                                                      "2) Maya Patil\n"));
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
