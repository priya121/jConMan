package test;

import conMan.contactfields.Contact;
import conMan.inputoutput.InputOutput;
import org.junit.Test;
import test.inputOutput.FakeIO;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ContactTest {
    private InputOutput console = new FakeIO(Arrays.asList("Priya", "Patil", "123@gmail.com", "2 Rosebury Av", "04.05.06", "123", "www"));
    Contact contact = new Contact(console);

    @Test
    public void contactHasAName() {
        contact.setFields();
        assertEquals("Priya Patil", contact.getName());
    }

    @Test
    public void userCanSetAndShowFieldsOfAContact() {
        InputOutput console = new FakeIO(Arrays.asList("Ben", "Smith", "123@gmail.com", "1 Rosebury Av", "04.05.06", "123", "www"));
        Contact ben = new Contact(console);
        List<Contact> contacts = Arrays.asList(ben);
        ben.setFields();
        assertEquals("First Name: Ben\n" +
                     "Last Name: Smith\n" +
                     "Email: 123@gmail.com\n" +
                     "Home Address: 1 Rosebury Av\n" +
                     "D.O.B: 04.05.06\n" +
                     "Phone Number: 123\n" +
                     "Website: www\n\n\n", contacts.get(0).showFields());
    }
}
