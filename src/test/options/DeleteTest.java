package test.options;

import main.ConMan;
import main.contactfields.Contact;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import main.options.Delete;
import main.options.Option;
import org.junit.Test;
import test.FakeExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeleteTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    Contact ben = createContact("Ben\nSmith\n234@gmail.com\n2 Rosebury Av\n");
    Contact priya = createContact("Priya\nPatil\n123@gmail.com\n3 Rosebury Av\n");
    InputOutput consoleIO = input("1\nBen\nSmith\n345@gmail.com\n2 Rosebury Av\n4\n1\n5\n");
    Option exitOption = new FakeExit(consoleIO);
    ConMan conMan = new ConMan(consoleIO, exitOption);

    @Test
    public void deleteHasADeleteTitle() {
        List<Contact> contacts = Arrays.asList(priya, ben);
        Delete delete = new Delete(contacts, consoleIO);
        delete.show();
        assertTrue(recordedOutput.toString().contains("Delete a contact "));
    }

    @Test
    public void userEntering4ShowsDeleteAContactTitle() {
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Delete a contact "));
    }

    @Test
    public void userAbleToDeleteAContact() {
        InputOutput console = input("Ben\nSmith\n123@gmail.com\n1 Cedar Way\n" +
                                  "Sarah\nSmith\n678@gmail.com\n2 Cedar Way\n1\n");
        List<Contact> contacts = createContactList(console);
        Delete delete = new Delete(contacts, console);
        delete.perform();
        assertEquals("Sarah Smith", contacts.get(0).getName());
    }

    @Test
    public void userMustEnterAValidNumberToDeleteAContact() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n2 Cedar Way\n" +
                                      "4\na\n1\n5\n");
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Please enter a valid number: "));
    }

    @Test
    public void userCanReadListOfContactsBeforeDecidingWhichToDelete() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                      "1\nMaya\nPatil\n345@gmail.com\n2 Cedar Way\n" +
                                      "1\nBen\nSmith\n123@gmail.com\n3 Cedar Way\n" +
                                      "4\n1\n5\n");
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("1) Priya Patil\n" +
                                                      "2) Maya Patil\n" +
                                                      "3) Ben Smith\n"));
    }

    private InputOutput input(String input) {
        return new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
    }

    private Contact createContact(String input) {
        ConsoleIO console = new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
        return new Contact(console);
    }

    private void setFields(List<Contact> contacts) {
        for (Contact contact : contacts) {
            contact.setFields();
        }
    }

    private List<Contact> createContactList(InputOutput console) {
        List<Contact> contacts = new ArrayList<>();
        Contact ben = new Contact(console);
        Contact sarah = new Contact(console);
        contacts.add(sarah);
        contacts.add(ben);
        setFields(contacts);
        return contacts;
    }
}
