package test;

import main.ConMan;
import main.Contact;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConManTest {
    ConMan conMan = new ConMan();
    Contact priya = new Contact("Priya", "Patil");
    Contact sarah = new Contact("Sarah", "Smith");

    @Test
    public void canReadACreatedContact() {
        conMan.create(priya);
        assertEquals("Priya Patil", conMan.read(1));
    }

    @Test
    public void readsAChosenContactFromList() {
        createContacts(Arrays.asList(sarah, priya));
        assertEquals("Priya Patil", conMan.read(2));
    }

    @Test
    public void canUpdateTheFirstNameOfACreatedContact() {
        conMan.create(priya);
        conMan.updateFirstName(1, "Sophie");
        assertEquals("Sophie Patil", conMan.read(1));
    }

    @Test
    public void canUpdateTheLastNameOfAContact() {
        conMan.create(priya);
        conMan.updateLastName(1, "Smith");
        assertEquals("Priya Smith", conMan.read(1));
    }

    @Test
    public void canDeleteACreatedContact() {
        createContacts(Arrays.asList(priya, sarah));
        conMan.delete(1);
        assertEquals("Sarah Smith", conMan.read(1));
    }

    @Test
    public void formatsTheNamesIntoANumberedList() {
        createContacts(Arrays.asList(sarah, priya));
        assertEquals("1) Sarah Smith\n" +
                     "2) Priya Patil\n", conMan.listAllNames());
    }

    private void createContacts(List<Contact> contacts) {
        contacts.forEach(conMan::create);
    }
}
