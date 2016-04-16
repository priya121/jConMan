package test;

import conMan.ConMan;
import conMan.ContactList;
import conMan.contactfields.Contact;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.Option;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ContactListTest {
    private Contact Ben;
    private Contact Sarah;
    private Contact Priya;
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    private ContactList contactList;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private ContactList allContacts;
    private File output;

    @Before
     public void setUp() throws IOException {
        Ben = createContact("Ben\nSmith\n234@gmail.com\n2 Rosebury Av\n");
        Sarah = createContact("Sarah\nSmith\n234@gmail.com\n2 Cedar Way\n");
        Priya = createContact("Priya\nPatil\n123@gmail.com\n3 Rosebury Av\n");
        setContactFields();
        addContactsToList();
        allContacts = new ContactList();
        output = temporaryFolder.newFile("output.txt");
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

    @Test
    public void contactWrittenToFileInJSONFormat() throws IOException {
        InputOutput console = input("1\nBen\nSmith\n234@gmail.com\n2 Rosebury Av\n5\nY\n");
        ContactList allContacts = new ContactList();
        Option exit = new FakeExit(console, allContacts, output);
        ConMan conMan = new ConMan(console, exit, output, allContacts);
        conMan.menuLoop();
        assertEquals("{\"Last Name: \":\"Smith \",\"Email: \":\"234@gmail.com \"" +
                     ",\"Home Address: \":\"2 Rosebury Av \"," +
                      "\"First Name: \":\"Ben \"}\n", readTempFile(output.getPath(), 108));
    }

    @Test
    public void contactsCanBeImportedFromAJSONFile() throws IOException {
        File input = new File("/Users/priyapatil/Work/conMan.json");
        InputOutput console = input("2\n1\n5\nY\n");
        Option exit = new FakeExit(console, allContacts, input);
        ConMan conMan = new ConMan(console, exit, input, allContacts);
        conMan.menuLoop();
        assertEquals("First Name: Priya \n" +
                "Last Name: Patil \n" +
                "Email: 123@gmail.com \n" +
                "Home Address: 12 Cedar Way \n\n\n", conMan.readContact(1));
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

    private InputOutput input(String input) {
        return new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
    }

    public String readTempFile(String inputFilePath, int amountOfBytes) throws IOException {
        File inputFile = new File(inputFilePath);
        InputStream input = new FileInputStream(inputFile);
        byte[] buffer = new byte[amountOfBytes];
        input.read(buffer);
        return new String(buffer);
    }
}
