package test;

import conMan.NameList;
import conMan.contactfields.Contact;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import org.junit.Ignore;
import org.junit.Test;
import test.inputOutput.FakeIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NameListTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    Contact Ben = createContact(Arrays.asList("Ben", "Smith", "123@gmail.com", "1 Cedar Way"));
    Contact Sarah = createContact(Arrays.asList("Sarah", "Smith", "234@gmail.com", "2 Cedar Way"));
    List<Contact> contacts = new ArrayList<>();

    @Test
    public void canFilterOutNamesInTheList() throws IOException {
        createContacts();
        InputOutput console = new ConsoleIO(new ByteArrayInputStream("Sar\nq\n".getBytes()), out);
        NameList list = new NameList(contacts, console);
        assertEquals("1) Sarah Smith\n", list.listNames(list.filter()));
    }

    @Ignore
    @Test
    public void canFilterOutByCombinationsOfLettersInTheList() {
        createContacts();
        InputOutput console = new ConsoleIO(new ByteArrayInputStream("Be\nn\nq\n".getBytes()), out);
        NameList list = new NameList(contacts, console);
        assertEquals("1) Ben Smith\n", list.listNames(list.filter()));
    }

    private Contact createContact(List<String> userInput) {
        InputOutput console = new FakeIO(userInput);
        return new Contact(console);
    }

    private void setContactFields(List<Contact> contacts) {
        for (Contact contact : contacts) {
            contact.setFields();
        }
    }

    private void createContacts() {
        contacts.add(Ben);
        contacts.add(Sarah);
        setContactFields(contacts);
    }
}
