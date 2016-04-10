package test.options;

import main.ConMan;
import main.Contact;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import main.options.Update;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class UpdateTest {
    Contact maya = new Contact("Maya", "Patil", "123@gmail.com");
    Contact sarah = new Contact("Sarah", "Smith", "234@gmail.com");
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("3\n".getBytes()), out);
    List<Contact> contacts = Arrays.asList(maya, sarah);
    ConMan conMan = new ConMan(consoleIO);

    @Test
    public void updateHasAUpdateTitle() {
        Update update = new Update(contacts, consoleIO);
        update.show();
        assertTrue(recordedOutput.toString().contains("Update a contact's details: \n"));
    }

    @Test
    public void userEntering1ShowsUpdateAContactTitle() {
        conMan.showGreeting();
        conMan.takeUserChoice();
        assertTrue(recordedOutput.toString().contains("Update a contact's details: \n"));
    }
}
