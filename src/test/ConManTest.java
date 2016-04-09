package test;

import main.ConMan;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConManTest {
    ConMan conMan = new ConMan();

    @Test
    public void canReadACreatedContact() {
        conMan.create("Priya");
        assertEquals("Priya", conMan.read());
    }

    @Test
    public void canUpdateACreatedContact() {
        conMan.create("Priya");
        conMan.update("Sophie");
        assertEquals("Sophie", conMan.read());
    }

    @Test
    public void canDeleteACreatedContact() {
        conMan.create("Priya");
        conMan.delete();
        assertEquals("", conMan.read());
    }

    @Test
    public void createsAListOfTwoContacts() {
        List<String> contacts = new ArrayList<>();
        contacts.add("Sarah");
        contacts.add("Priya");
        conMan.create("Sarah");
        conMan.create("Priya");
        assertEquals(contacts, conMan.getAllContacts());
    }
}