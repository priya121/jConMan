package test.options;

import main.ConMan;
import main.contactfields.Contact;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import main.options.Read;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ReadTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    Contact priya = createContact(new ByteArrayInputStream("Priya\nPatil\n123@gmail.com\n".getBytes()));
    Contact sarah = createContact(new ByteArrayInputStream("Sarah\nBlack\n234@gmail.com\n2\n1\n".getBytes()));
    List<Contact> contacts = Arrays.asList(priya, sarah);
    InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("Priya\nPatil\n123@gmail.com\n2\n1\n".getBytes()), out);
    ConMan conMan = new ConMan(consoleIO);

    @Test
    public void readHasAReadTitle() {
        Read read = new Read(contacts, consoleIO);
        read.show();
        assertTrue(recordedOutput.toString().contains("Read a contact's details: \n"));
    }

    @Test
    public void userEntering2ShowsReadAContactTitle() {
        conMan.showGreeting();
        conMan.create();
        conMan.takeUserChoice();
        assertTrue(recordedOutput.toString().contains("Read a contact's details:"));
    }

    @Test
    public void readFirstDisplaysAListOfNames() {
        conMan.create();
        conMan.takeUserChoice();
        assertTrue(recordedOutput.toString().contains("First Name: Priya\n" +
                "Last Name: Patil\n" +
                "Email: 123@gmail.com\n"));
    }

    private Contact createContact(InputStream inputStream) {
        ConsoleIO console = new ConsoleIO(inputStream, out);
        return new Contact(console);
    }
}
