package test.options;

import main.ConMan;
import main.Contact;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import main.options.Create;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CreateTest {
    Contact ben = new Contact("Ben", "Smith", "123@gmail.com");
    Contact sarah = new Contact("Sarah", "Smith", "234@gmail.com");
    List<Contact> contacts = Arrays.asList(ben, sarah);
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("1\n".getBytes()), out);
    ConMan conMan = new ConMan(consoleIO);

    @Test
    public void createChoiceHasACreateTitle() {
        Create createOption = new Create(contacts, consoleIO);
        createOption.show();
        assertTrue(recordedOutput.toString().contains("Create a contact: \n"));
    }

    @Test
    public void userEntering1ShowsCreateAContactTitle() {
        conMan.showGreeting();
        conMan.takeUserChoice();
        assertTrue(recordedOutput.toString().contains("Create a contact: \n"));
    }
}
