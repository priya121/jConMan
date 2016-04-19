package test;

import conMan.ConMan;
import conMan.ContactList;
import fileTypes.CSVFile;
import fileTypes.FileType;
import fileTypes.JSONFile;
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
    private ContactList importedContacts;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private ContactList contactList;
    private File output;

    @Before
     public void setUp() throws IOException {
        Ben = createContact("Ben\nSmith\n234@gmail.com\n2 Rosebury Av\n123\n");
        Sarah = createContact("Sarah\nSmith\n234@gmail.com\n2 Cedar Way\n234\n");
        Priya = createContact("Priya\nPatil\n123@gmail.com\n3 Rosebury Av\n567\n");
        setContactFields();
        addContactsToList();
        contactList = new ContactList();
        output = temporaryFolder.newFile("output.txt");
    }

    @Test
    public void contactsSavedToContactListEverTimeNewContactCreated() {
        assertEquals(3, importedContacts.getList().size());
    }

    @Test
    public void contactsSavedInOrderCreated() {
        Contact secondContact = importedContacts.get(1);
        assertEquals("Sarah Smith", secondContact.getName());
    }

    @Test
    public void contactWrittenToFileInJSONFormat() throws IOException {
        InputOutput console = input("1\nBen\nSmith\n234@gmail.com\n2 Rosebury Av\n123\n5\nY\n");
        ContactList allContacts = new ContactList();
        FileType jsonFile = new JSONFile(output, console, allContacts);
        Option exit = new FakeExit(console, allContacts, jsonFile);
        ConMan conMan = new ConMan(console, exit, jsonFile, allContacts);
        conMan.menuLoop();
        assertEquals("{\"Last Name: \":\"Smith\",\"Email: \":\"234@gmail.com\"" +
                     ",\"Home Address: \":\"2 Rosebury Av\"," +
                      "\"First Name: \":\"Ben\",\"Phone Number: \":\"123\"}", readTempFile(output.getPath(), 126));
    }

    @Test
    public void contactWrittenToFileInCSVFormat() throws IOException {
        InputOutput console = input("1\nBen\nSmith\n234@gmail.com\n2 Rosebury Av\n123\n" +
                                    "1\nSarah\nSmith\n456@gmail.com\n6 Forlease Road\n345\n5\nY\n");
        ContactList allContacts = new ContactList();
        FileType CSVfile = new CSVFile(output, console, allContacts);
        Option exit = new FakeExit(console, allContacts, CSVfile);
        ConMan conMan = new ConMan(console, exit, CSVfile, allContacts);
        conMan.menuLoop();
        assertEquals("Ben,Smith,234@gmail.com,2 Rosebury Av,123,\n" +
                     "Sarah,Smith,456@gmail.com,6 Forlease Road,345,\n", readTempFile(output.getPath(), 90));
    }

    @Test
    public void contactCanBeImportedFromAFile() throws IOException {
        InputOutput console = input("2\nN\n1\n5\nY\n");
        FileType fakeFile = new FakeFile(console, contactList, importedContacts);
        Option exit = new FakeExit(console, contactList, fakeFile);
        ConMan conMan = new ConMan(console, exit, fakeFile, contactList);
        conMan.menuLoop();
        assertEquals("First Name: Priya\n" +
                     "Last Name: Patil\n" +
                     "Email: 123@gmail.com\n" +
                     "Home Address: 3 Rosebury Av\n" +
                     "Phone Number: 567\n\n\n", contactList.get(2).showFields());
    }

    @Test
    public void contactsSavedToFileOnExit() {
        InputOutput console = input("1\nGeorge\nBlack\n678@gmail.com\n3 Rosebury Av\n123\n" +
                                    "2\nN\n1\n5\nY\n");
        FakeFile fakeFile = new FakeFile(console, contactList, importedContacts);
        Option exit = new FakeExit(console, contactList, fakeFile);
        ConMan conMan = new ConMan(console, exit, fakeFile, contactList);
        conMan.menuLoop();
        assertEquals(4, fakeFile.savedContacts.size());
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
        importedContacts = new ContactList();
        importedContacts.addContact(Ben);
        importedContacts.addContact(Sarah);
        importedContacts.addContact(Priya);
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
