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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ReadTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    Contact priya = createContact(new ByteArrayInputStream("Priya\nPatil\n123@gmail.com\n1 Cedar Way\n".getBytes()));
    Contact sarah = createContact(new ByteArrayInputStream(("Sarah\nBlack\n234@gmail.com\n2 Cedar Way\n" +
                                                            "2\n1\n").getBytes()));
    List<Contact> contacts = new ArrayList<>();
    InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                                                    "2\n1\n").getBytes()), out);


    @Test
    public void readHasAReadTitle() {
        Read read = new Read(contacts, consoleIO);
        read.show();
        assertTrue(recordedOutput.toString().contains("Read a contact's details \n"));
    }

    @Test
    public void userEntering2ShowsReadAContactsDetailsTitle() {
        ConMan conMan = new ConMan(consoleIO);
        conMan.showGreeting();
        conMan.optionSelected();
        conMan.optionSelected();
        assertTrue(recordedOutput.toString().contains("Read a contact's details \n"));
    }

    @Test
    public void userEntering1ReadsFirstContact() {
        createContactsList();
        Read read = new Read(contacts, consoleIO);
        read.perform();
        assertTrue(recordedOutput.toString().contains("First Name: Priya\n" +
                                                      "Last Name: Patil\n" +
                                                      "Email: 123@gmail.com\n" +
                                                      "Home Address: 1 Cedar Way\n"));
    }

    @Test
    public void readFirstDisplaysAListOfNames() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nPriya\nPatil\n123@gmail.com\n2 Cedar Way\n" +
                                                                        "1\nSarah\nBlack\n345@gmail.com\n 3 Cedar Way\n" +
                                                                        "2\n1\n").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO);
        createTwoContacts(conMan);
        conMan.optionSelected();
        assertTrue(recordedOutput.toString().contains("1) Priya Patil\n" +
                                                      "2) Sarah Black\n"));
    }


    private void createTwoContacts(ConMan conMan) {
        conMan.optionSelected();
        conMan.optionSelected();
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

    private void createContactsList() {
        contacts.add(priya);
        contacts.add(sarah);
        setFields(contacts);
    }
}
