package test;

import main.Contact;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContactTest {

    @Test
    public void contactHasAFirstName() {
        Contact contact = new Contact("Priya", "Patil");
        assertEquals("Priya", contact.getFirstName());
    }

    @Test
    public void contactHasLastName() {
        Contact contact = new Contact("Priya", "Patil");
        assertEquals("Patil", contact.getLastName());
    }

    @Test
    public void canGetContactFullName() {
        Contact contact = new Contact("Priya", "Patil");
        assertEquals("Priya Patil", contact.getName());
    }
}
