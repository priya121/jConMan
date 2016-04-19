package test;

import conMan.ContactList;
import conMan.NameList;
import conMan.contactfields.Contact;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import org.junit.Test;
import test.inputOutput.FakeIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NameListTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    Contact Ben = createContact(Arrays.asList("Ben", "Smith", "123@gmail.com", "1 Cedar Way", "123", "www"));
    Contact Sarah = createContact(Arrays.asList("Sarah", "Smith", "234@gmail.com", "2 Cedar Way", "123", "www"));
    ContactList list = new ContactList();

    @Test
    public void canFilterOutNamesInTheList() throws IOException {
        createContacts();
        InputOutput console = new ConsoleIO(new ByteArrayInputStream("Sarah\n".getBytes()), out);
        NameList namesList = new NameList(list, console);
        assertEquals("1) Sarah Smith\n", namesList.formatNames(namesList.filter()));
    }

    @Test
    public void canFilterOutByCombinationsOfLettersInTheList() {
        createContacts();
        InputOutput console = new ConsoleIO(new ByteArrayInputStream("Ben\n".getBytes()), out);
        NameList namesList = new NameList(list, console);
        assertEquals("1) Ben Smith\n", namesList.formatNames(namesList.filter()));
    }

    @Test
    public void asksUserToFilterAgainIfNoMatchingContactsFound() {
        createContacts();
        InputOutput console = new ConsoleIO(new ByteArrayInputStream("Y\nBn\nN\n1\n".getBytes()), out);
        NameList namesList = new NameList(list, console);
        namesList.filterCheck();
        assertTrue(recordedOutput.toString().contains("There are no contacts with that name\n"));
    }

    @Test
    public void returnsContactIfFoundOnSecondAttempt() {
        createContacts();
        InputOutput console = new ConsoleIO(new ByteArrayInputStream("Y\nBn\nY\nBen\n".getBytes()), out);
        NameList namesList = new NameList(list, console);
        assertEquals("Ben Smith", namesList.filterCheck().get(0).getName());
    }

    private Contact createContact(List<String> userInput) {
        InputOutput console = new FakeIO(userInput);
        return new Contact(console);
    }

    private void setContactFields(ContactList list) {
        for (Contact contact : list.get()) {
            contact.setFields();
        }
    }

    private void createContacts() {
        list.addContact(Ben);
        list.addContact(Sarah);
        setContactFields(list);
    }
}
