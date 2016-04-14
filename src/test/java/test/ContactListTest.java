package test;

import conMan.ContactList;
import conMan.contactfields.Contact;
import conMan.inputoutput.ConsoleIO;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ContactListTest {
    private Contact Ben;
    private Contact Sarah;
    private Contact Priya;
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    private ContactList contactList;

    @Before
     public void setUp() {
        Ben = createContact("Ben\nSmith\n234@gmail.com\n2 Rosebury Av\n");
        Sarah = createContact("Sarah\nSmith\n234@gmail.com\n2 Cedar Way\n");
        Priya = createContact("Priya\nPatil\n123@gmail.com\n3 Rosebury Av\n");
        setContactFields();
        addContactsToList();
    }

    @Test
    public void contactsSavedToContactListEverTimeNewContactCreated() {
        assertEquals(3, contactList.getList().size());
    }

    @Test
    public void contactsSavedInOrderCreated() {
        Contact secondContact = contactList.get(1);
        assertEquals("Sarah Smith", secondContact.getName());
    }

    private Contact createContact(String input) {
        ConsoleIO console = new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
        return new Contact(console);
    }

    private void setContactFields() {
        Ben.setFields();
        Sarah.setFields();
        Priya.setFields();
    }

    private void addContactsToList() {
        contactList = new ContactList();
        contactList.addContact(Ben);
        contactList.addContact(Sarah);
        contactList.addContact(Priya);
    }
}
