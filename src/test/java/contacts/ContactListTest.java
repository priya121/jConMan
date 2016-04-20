package contacts;

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
import test.FakeExit;
import file.FakeFile;

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
    private FakeFile fakeFile;

    @Before
     public void setUp() throws IOException {
        Ben = createContact("Ben\nSmith\n234@gmail.com\n2 Rosebury Av\n04.05.06\n123\nwww\n\n");
        Sarah = createContact("Sarah\nSmith\n234@gmail.com\n2 Cedar Way\n04.05.06\n234\nwww\n\n");
        Priya = createContact("Priya\nPatil\n123@gmail.com\n3 Rosebury Av\n04.05.06\n567\nwww\n\n");
        setContactFields();
        addContactsToList();
        contactList = new ContactList();
        output = temporaryFolder.newFile("output.txt");
        fakeFile = new FakeFile(contactList, importedContacts);
    }

    @Test
    public void contactsSavedToContactListEverTimeNewContactCreated() {
        assertEquals(3, importedContacts.get().size());
    }

    @Test
    public void contactsSavedInOrderCreated() {
        Contact secondContact = importedContacts.getContact(1);
        assertEquals("Sarah Smith", secondContact.getName());
    }

    @Test
    public void contactWrittenToFileInJSONFormat() throws IOException {
        InputOutput console = input("1\nBen\nSmith\n234@gmail.com\n2 Rosebury Av\n04.05.06\n123\nwww\n\n5\nY\n");
        ContactList allContacts = new ContactList();
        FileType jsonFile = new JSONFile(output, console, allContacts);
        Option exit = new FakeExit(console, jsonFile);
        ConMan conMan = new ConMan(console, exit, jsonFile, allContacts);
        conMan.menuLoop();
        assertEquals("{\"Last Name: \":\"Smith\",\"Email: \":\"234@gmail.com\"" +
                     ",\"Home Address: \":\"2 Rosebury Av\"," +
                      "\"First Name: \":\"Ben\",\"Phone Number: \":\"123\",\"Website: \":\"www\",\"D.O.B: \":\"04.05.06\"}\n", readTempFile(output.getPath(), 166));
    }

    @Test
    public void contactWrittenToFileInCSVFormat() throws IOException {
        InputOutput console = input("1\nBen\nSmith\n234@gmail.com\n2 Rosebury Av\n04.05.06\n123\nwww\n\n" +
                                    "1\nSarah\nSmith\n456@gmail.com\n6 Forlease Road\n04.05.06\n345\nwww\n\n5\nY\n");
        ContactList allContacts = new ContactList();
        FileType CSVfile = new CSVFile(output, console, allContacts);
        Option exit = new FakeExit(console, CSVfile);
        ConMan conMan = new ConMan(console, exit, CSVfile, allContacts);
        conMan.menuLoop();
        assertEquals("Ben,Smith,234@gmail.com,2 Rosebury Av,04.05.06,123,www,\n" +
                     "Sarah,Smith,456@gmail.com,6 Forlease Road,04.05.06,345,www,\n", readTempFile(output.getPath(), 116));
    }

    @Test
    public void contactCanBeImportedFromAFakeFile() throws IOException {
        InputOutput console = input("2\nN\n1\n\n5\nY\n");
        Option exit = new FakeExit(console, fakeFile);
        ConMan conMan = new ConMan(console, exit, fakeFile, contactList);
        conMan.menuLoop();
        assertEquals("First Name: Priya\n" +
                     "Last Name: Patil\n" +
                     "Email: 123@gmail.com\n" +
                     "Home Address: 3 Rosebury Av\n" +
                     "D.O.B: 04.05.06\n" +
                     "Phone Number: 567\n" +
                     "Website: www\n\n\n", contactList.getContact(2).showFields());
    }

    @Test
    public void contactsCanBeImportedFromACSVFile() {
        InputOutput console = input("");
        File CSVPath = new File("1000Contacts.csv");
        FileType jsonFile = new CSVFile(CSVPath, console, contactList);
        jsonFile.importContacts();
        assertEquals(1000, contactList.get().size());
    }

    @Test
    public void contactsCanBeAddedFromAJSONFile() {
        InputOutput console = input("");
        File CSVPath = new File("10Contacts.json");
        FileType jsonFile = new CSVFile(CSVPath, console, contactList);
        jsonFile.importContacts();
        assertEquals(10, contactList.get().size());
    }

    @Test
    public void contactsSavedToFileOnExit() {
        InputOutput console = input("1\nGeorge\nBlack\n678@gmail.com\n3 Rosebury Av\n04.05.06\n123\nwww\n\n" +
                                    "2\nN\n1\n\n5\nY\n");
        Option exit = new FakeExit(console, fakeFile);
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
