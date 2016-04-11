package test;

import main.contactfields.Contact;
import main.inputoutput.InputOutput;
import org.junit.Test;
import test.inputOutput.FakeIO;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ContactTest {
    private InputOutput console = new FakeIO(Arrays.asList("Priya", "Patil", "123@gmail.com", "Priya", "Patil", "567@gmail.com"));
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
}
