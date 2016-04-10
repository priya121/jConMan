package test.options;

import main.ConMan;
import main.Contact;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import main.options.Read;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ReadTest {
    Contact priya = new Contact("Priya", "Patil", "123@gmail.com");
    Contact sarah = new Contact("Sarah", "Smith", "234@gmail.com");
    List<Contact> contacts = Arrays.asList(priya, sarah);
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("2\n".getBytes()), out);
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
        conMan.takeUserChoice();
        assertTrue(recordedOutput.toString().contains("Read a contact's details: \n"));
    }
}
