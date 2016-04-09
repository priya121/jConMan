package test;

import main.ConMan;
import main.Contact;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConManTest {
    ConMan conMan = new ConMan();
    Contact priya = new Contact("Priya", "Patil", "123@gmail.com");
    Contact sarah = new Contact("Sarah", "Smith", "234@gmail.com");

    @Test
    public void canReadACreatedContact() {
        conMan.create(priya);
        assertEquals("Priya Patil", conMan.readName(1));
    }

    @Test
    public void readsAChosenContactFromList() {
        createContacts(Arrays.asList(sarah, priya));
        assertEquals("Priya Patil", conMan.readName(2));
    }

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

    @Test
    public void canDeleteACreatedContact() {
        createContacts(Arrays.asList(priya, sarah));
        conMan.delete(1);
        assertEquals("Sarah Smith", conMan.readName(1));
    }

    @Test
    public void formatsTheNamesIntoANumberedList() {
        createContacts(Arrays.asList(sarah, priya));
        assertEquals("1) Sarah Smith\n" +
                     "2) Priya Patil\n", conMan.listAllNames());
    }

    @Test
    public void readsContactInformationWhenSelected() {
        createContacts(Arrays.asList(sarah, priya));
        assertEquals("Name: Sarah Smith\n" +
                     "Email: 234@gmail.com\n", conMan.readContact(1));
    }

    @Test
    public void canUpdateEmailOfAClient() {
        createContacts(Arrays.asList(sarah, priya));
        conMan.updateEmail(2, "567@gmail.com");
        assertEquals("Name: Priya Patil\n" +
                     "Email: 567@gmail.com\n", conMan.readContact(2));

    }

    private void createContacts(List<Contact> contacts) {
        contacts.forEach(conMan::create);
    }
}
