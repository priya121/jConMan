package test.options;

import main.ConMan;
import main.Contact;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import main.options.Delete;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class DeleteTest {
    Contact priya = new Contact("Priya", "Patil", "123@gmail.com");
    Contact ben = new Contact("Ben", "Smith", "234@gmail.com");
    List<Contact> contacts = Arrays.asList(priya, ben);
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("4\n".getBytes()), out);
    ConMan conMan = new ConMan(consoleIO);

    @Test
    public void deleteHasADeleteTitle() {
        Delete delete = new Delete(contacts, consoleIO);
        delete.show();
        assertTrue(recordedOutput.toString().contains("Delete a contact: "));
    }

    @Test
    public void userEntering4ShowsDeleteAContactTitle() {
        conMan.showGreeting();
        conMan.takeUserChoice();
        assertTrue(recordedOutput.toString().contains("Delete a contact: "));
    }
}
