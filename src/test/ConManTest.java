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
        createContacts(Arrays.asList(sarah, priya));
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
        createContacts(Arrays.asList(priya, sarah));
        conMan.delete(1);
        assertEquals("Sarah", conMan.read(1));
    }

    @Test
    public void formatsTheNamesIntoANumberedList() {
        createContacts(Arrays.asList(sarah, priya));
        assertEquals("1) Sarah\n" +
                     "2) Priya\n", conMan.listAllNames());
    }

    private void createContacts(List<Contact> contacts) {
        for(Contact contact : contacts) {
            conMan.create(contact);
        }
    }
}
