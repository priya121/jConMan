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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeleteTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    Contact ben = createContact(new ByteArrayInputStream("Ben\nSmith\n234@gmail.com\n2 Rosebury Av\n".getBytes()));
    Contact priya = createContact(new ByteArrayInputStream("Priya\nPatil\n123@gmail.com\n3 Rosebury Av\n".getBytes()));
    ConsoleIO consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nBen\nSmith\n345@gmail.com\n2 Rosebury Av\n" +
                                                                  "4\n1\n").getBytes()), out);
    ConMan conMan = new ConMan(consoleIO);

    @Test
    public void deleteHasADeleteTitle() {
        List<Contact> contacts = Arrays.asList(priya, ben);
        Delete delete = new Delete(contacts, consoleIO);
        delete.show();
        assertTrue(recordedOutput.toString().contains("Delete a contact "));
    }

    @Test
    public void userEntering4ShowsDeleteAContactTitle() {
        conMan.showGreeting();
        conMan.optionSelected();
        conMan.optionSelected();
        assertTrue(recordedOutput.toString().contains("Delete a contact "));
    }

    @Test
    public void userAbleToDeleteAContact() {
        ConsoleIO console = new ConsoleIO(new ByteArrayInputStream(("Ben\nSmith\n123@gmail.com\n1 Cedar Way\n" +
                                                                    "Sarah\nSmith\n678@gmail.com\n2 Cedar Way\n1\n").getBytes()), out);
        List<Contact> contacts = createContactList(console);
        Delete delete = new Delete(contacts, console);
        delete.perform();
        assertEquals("Sarah Smith", contacts.get(0).getName());
    }



    private Contact createContact(InputStream inputStream) {
        ConsoleIO console = new ConsoleIO(inputStream, out);
        return new Contact(console);
    }

    private void setFields(List<Contact> contacts) {
        for (Contact contact : contacts) {
            contact.setFields();
        }
    }

    private List<Contact> createContactList(ConsoleIO console) {
        List<Contact> contacts = new ArrayList<>();
        Contact ben = new Contact(console);
        Contact sarah = new Contact(console);
        contacts.add(sarah);
        contacts.add(ben);
        setFields(contacts);
        return contacts;
    }
}
