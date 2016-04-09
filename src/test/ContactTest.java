package test;

import main.Contact;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContactTest {
    Contact contact = new Contact("Priya", "Patil", "123@gmail.com");

    @Test
    public void contactHasAFirstName() {
        assertEquals("Priya", contact.getFirstName());
    }

    @Test
    public void contactHasLastName() {
        assertEquals("Patil", contact.getLastName());
    }

    @Test
    public void canGetContactFullName() {
        assertEquals("Priya Patil", contact.getName());
    }

    @Test
    public void canChangeContactsFirstName() {
        contact.setFirstName("Sam");
        contact.setLastName("Smith");
        assertEquals("Sam Smith", contact.getName());
    }

    @Test
    public void contactHasAnEmailAddress() {
        assertEquals("123@gmail.com", contact.getEmail());
    }
}
