package test;

import main.ConMan;
import main.Contact;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UpdateTest {
    ConMan conMan = new ConMan();
    Contact priya = new Contact("Priya", "Patil", "123@gmail.com");

    @Test
    public void canUpdateTheFirstNameOfACreatedContact() {
        conMan.create(priya);
        conMan.updateFirstName(1, "Sophie");
        assertEquals("Sophie Patil", conMan.readName(1));
    }

    @Test
    public void canUpdateTheLastNameOfAContact() {
        conMan.create(priya);
        conMan.updateLastName(1, "Smith");
        assertEquals("Priya Smith", conMan.readName(1));
    }

    @Test
    public void canUpdateEmailAddressOfAContact() {
        conMan.create(priya);
    }


}
