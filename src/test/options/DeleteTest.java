package test.options;

import main.ConMan;
import main.contactfields.Contact;
import main.inputoutput.ConsoleIO;
import main.options.Delete;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class DeleteTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    Contact ben = createContact(new ByteArrayInputStream("Ben\nSmith\n234@gmail.com\n4\n".getBytes()));
    Contact priya = createContact(new ByteArrayInputStream("Priya\nPatil\n123@gmail.com\n".getBytes()));
    ConsoleIO consoleIO = new ConsoleIO(new ByteArrayInputStream("4\n".getBytes()), out);
    ConMan conMan = new ConMan(consoleIO);

    @Test
    public void deleteHasADeleteTitle() {
        List<Contact> contacts = Arrays.asList(priya, ben);
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

    private Contact createContact(InputStream inputStream) {
        ConsoleIO console = new ConsoleIO(inputStream, out);
        return new Contact(console);
    }
}
