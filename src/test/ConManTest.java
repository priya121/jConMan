package test;

import main.ConMan;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ConManTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                                                    "1\nSarah\nSmith\n234@gmail.com\n2 Cedar Way\n").getBytes()), out);
    ConMan conMan = new ConMan(consoleIO);

    @Test
    public void showsInitialGreeting() {
        ConMan conMan = new ConMan(consoleIO);
        conMan.showGreeting();
        assertEquals("Welcome to ConMan! \n" +
                "Please choose from the following options: \n", recordedOutput.toString());
    }

    @Test
    public void showsUserOptions() {
        ConMan conMan = new ConMan(consoleIO);
        conMan.showOptionTitles();
        assertEquals("1) Create a contact \n" +
                "2) Read a contact's details \n" +
                "3) Update a contact's details \n" +
                "4) Delete a contact \n", recordedOutput.toString());
    }

    @Test
    public void canReadCreatedContactsDetails() {
        createTwoContacts(conMan);
        assertEquals("First Name: Priya\n" +
                "Last Name: Patil\n" +
                "Email: 123@gmail.com\n" +
                "Home Address: 1 Cedar Way\n", conMan.readContact(1));
        assertEquals("First Name: Sarah\n" +
                "Last Name: Smith\n" +
                "Email: 234@gmail.com\n" +
                "Home Address: 2 Cedar Way\n", conMan.readContact(2));
    }

    @Test
    public void formatsTheNamesIntoANumberedList() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                                                        "1\nSarah\nSmith\n234@gmail.com\n2 Cedar Way").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO);
        createTwoContacts(conMan);
        assertEquals("1) Priya Patil\n" +
                "2) Sarah Smith\n", conMan.listAllNames());
    }

    @Test
    public void readsContactInformationDetailsWhenSelected() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("1\nSarah\nSmith\n234@gmail.com\n1 Cedar Way\n".getBytes()), out);
        ConMan conMan = new ConMan(consoleIO);
        conMan.optionSelected();
        assertEquals("First Name: Sarah\n" +
                     "Last Name: Smith\n" +
                     "Email: 234@gmail.com\n" +
                "Home Address: 1 Cedar Way\n", conMan.readContact(1));
    }

    @Test
    public void canUpdateTheFirstNameOfACreatedContact() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nSarah\nSmith\n234@gmail.com\n1 Cedar Way\n" +
                                                                        "3\n1\nSam\nSmith\n123@gmail.com\n2 Cedar Way\n").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO);
        conMan.optionSelected();
        conMan.optionSelected();
        assertEquals("First Name: Sam\n" +
                "Last Name: Smith\n" +
                "Email: 123@gmail.com\n" +
                "Home Address: 2 Cedar Way\n", conMan.readContact(1));
    }

    @Test
    public void canDeleteACreatedContact() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nSarah\nSmith\n234@gmail.com\n1 Cedar Way\n" +
                                                                        "1\nPriya\nPatil\n123@gmail.com\n2 Cedar Way\n" +
                                                                        "4\n1\n").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO);
        createTwoContacts(conMan);
        conMan.optionSelected();
        assertEquals("First Name: Priya\n" +
                "Last Name: Patil\n" +
                "Email: 123@gmail.com\n" +
                "Home Address: 2 Cedar Way\n", conMan.readContact(1));
    }

    private void createTwoContacts(ConMan conMan) {
        conMan.optionSelected();
        conMan.optionSelected();
    }
}
