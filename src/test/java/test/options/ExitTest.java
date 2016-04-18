package test.options;

import conMan.ConMan;
import conMan.ContactList;
import conMan.FileType;
import conMan.contactfields.Contact;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import test.FakeExit;
import test.FakeFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class ExitTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    ContactList allContacts = new ContactList();
    InputOutput console = input("Ben\nSmith\n123@gmail.com\n1 Cedar Way\n" +
                                "Sarah\nSmith\n678@gmail.com\n2 Cedar Way\n1\nY\n");
    ContactList contactList = createContactList(console);

    @Test
    public void userEntering5ExitsConMan() throws IOException {
        InputOutput console = new ConsoleIO(new ByteArrayInputStream("5\n".getBytes()), out);
        FileType fakeFile = new FakeFile(console, allContacts, contactList);
        FakeExit exitOption = new FakeExit(console, allContacts, fakeFile);
        ConMan conMan = new ConMan(console, exitOption, fakeFile, allContacts);
        conMan.menuChoice();
        assertTrue(recordedOutput.toString().contains("Exiting ConMan..."));
    }

    private InputOutput input(String input) {
        return new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
    }

    private ContactList createContactList(InputOutput console) {
        ContactList contactList = new ContactList();
        Contact ben = new Contact(console);
        Contact sarah = new Contact(console);
        contactList.addContact(ben);
        contactList.addContact(sarah);
        setFields(contactList);
        return contactList;
    }

    private void setFields(ContactList contacts) {
        for (Contact contact : contacts.getList()) {
            contact.setFields();
        }
    }
}
