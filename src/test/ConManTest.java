package test;

import main.ConMan;
import main.Contact;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConManTest {
    ConMan conMan = new ConMan();
    Contact priya = new Contact("Priya");
    Contact sarah = new Contact("Sarah");

    @Test
    public void canReadACreatedContact() {
        conMan.create(priya);
        assertEquals("Priya", conMan.read(1));
    }

    @Test
    public void readsAChosenContactFromList() {
        conMan.create(sarah);
        conMan.create(priya);
        assertEquals("Priya", conMan.read(2));
    }

    @Test
    public void canUpdateACreatedContact() {
        conMan.create(priya);
        conMan.update(1, "Sophie");
        assertEquals("Sophie", conMan.read(1));
    }

    @Test
    public void canDeleteACreatedContact() {
        conMan.create(priya);
        conMan.create(sarah);
        conMan.delete(1);
        assertEquals("Sarah", conMan.read(1));
    }

    @Test
    public void createsAListOfTwoContacts() {
        List<String> contacts = Arrays.asList("Sarah", "Priya");
        conMan.create(sarah);
        conMan.create(priya);
        assertEquals(contacts, conMan.getAllContacts());
    }

    @Test
    public void formatsTheNamesInANumberedList() {
        conMan.create(sarah);
        conMan.create(priya);
        assertEquals("1) Sarah\n" +
                     "2) Priya\n", conMan.numbersContacts());
    }
}
