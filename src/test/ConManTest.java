package test;

import main.ConMan;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConManTest {
    ConMan conMan = new ConMan();

    @Test
    public void canReadACreatedContact() {
        conMan.create("Priya");
        assertEquals("Priya", conMan.read(1));
    }

    @Test
    public void readsAChosenContactFromList() {
        conMan.create("Sarah");
        conMan.create("Priya");
        assertEquals("Priya", conMan.read(2));
    }

    @Test
    public void canUpdateACreatedContact() {
        conMan.create("Priya");
        conMan.update(1, "Sophie");
        assertEquals("Sophie", conMan.read(1));
    }

    @Test
    public void canDeleteACreatedContact() {
        conMan.create("Priya");
        conMan.create("Sarah");
        conMan.delete(1);
        assertEquals("Sarah", conMan.read(1));
    }

    @Test
    public void createsAListOfTwoContacts() {
        List<String> contacts = Arrays.asList("Sarah", "Priya");
        conMan.create("Sarah");
        conMan.create("Priya");
        assertEquals(contacts, conMan.getAllContacts());
    }

    @Test
    public void formatsTheNamesInANumberedList() {
        conMan.create("Sarah");
        conMan.create("Priya");
        assertEquals("1) Sarah\n" +
                     "2) Priya\n", conMan.numbersContacts());
    }
}
