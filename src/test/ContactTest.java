package test;

import main.Contact;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContactTest {

    @Test
    public void contactHasNameField() {
        Contact contact = new Contact("Priya");
        assertEquals("Priya", contact.getName());
    }
}
