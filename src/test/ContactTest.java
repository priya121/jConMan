package test;

import main.contactfields.Contact;
import main.inputoutput.InputOutput;
import org.junit.Test;
import test.inputOutput.FakeIO;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ContactTest {
    private InputOutput console = new FakeIO(Arrays.asList("Priya", "Patil", "123@gmail.com", "2 Rosebury Av"));
    Contact contact = new Contact(console);

    @Test
    public void contactHasAName() {
        contact.setFields();
        assertEquals("Priya Patil", contact.getName());
    }

    @Test
    public void contactHasAnEmailAddress() {
        contact.setFields();
        assertEquals("123@gmail.com", contact.getEmail());
    }

    @Test
    public void userCanSetFieldsOfAContact() {
        InputOutput console = new FakeIO(Arrays.asList("Ben", "Smith", "123@gmail.com", "1 Rosebury Av"));
        Contact ben = new Contact(console);
        List<Contact> contacts = Arrays.asList(ben);
        ben.setFields();
        assertEquals("First Name: Ben\n" +
                "Last Name: Smith\n" +
                "Email: 123@gmail.com\n" +
                "Home Address: 1 Rosebury Av\n", contacts.get(0).showFields());
    }
}
