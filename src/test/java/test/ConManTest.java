package test;

import conMan.ConMan;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.Option;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConManTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                                                    "1\nSarah\nSmith\n234@gmail.com\n2 Cedar Way\n5\n").getBytes()), out);
    Option exitOption = new FakeExit(consoleIO);
    ConMan conMan = new ConMan(consoleIO, exitOption);

    @Test
    public void showsInitialGreeting() {
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.showGreeting();
        assertEquals("Welcome to conMan.ConMan! \n" +
                     "Please choose from the following conMan.options: \n", recordedOutput.toString());
    }

    @Test
    public void showsUserOptions() {
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.showOptionTitles();
        assertEquals("1) Create a contact \n" +
                     "2) Read a contact's details \n" +
                     "3) Update a contact's details \n" +
                     "4) Delete a contact \n" +
                     "5) ExitConMan conMan.ConMan \n", recordedOutput.toString());
    }

    @Test
    public void canReadCreatedContactsDetails() {
        conMan.menuLoop();
        assertEquals("First Name: Sarah\n" +
                     "Last Name: Smith\n" +
                     "Email: 234@gmail.com\n" +
                     "Home Address: 2 Cedar Way\n", conMan.readContact(2));
    }

    @Test
    public void formatsTheNamesIntoANumberedList() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                                                        "1\nSarah\nSmith\n234@gmail.com\n2 Cedar Way\n5\n").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertEquals("1) Priya Patil\n" +
                     "2) Sarah Smith\n", conMan.listAllNames());
    }

    @Test
    public void readsContactInformationDetailsWhenSelected() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("1\nSarah\nSmith\n234@gmail.com\n1 Cedar Way\n".getBytes()), out);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.optionSelected();
        assertEquals("First Name: Sarah\n" +
                     "Last Name: Smith\n" +
                     "Email: 234@gmail.com\n" +
                     "Home Address: 1 Cedar Way\n", conMan.readContact(1));
    }

    @Test
    public void canUpdateTheFirstNameOfACreatedContact() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nSarah\nSmith\n234@gmail.com\n1 Cedar Way\n" +
                                                                        "3\n1\nSam\nSmith\n123@gmail.com\n2 Cedar Way\n5\n").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertEquals("First Name: Sam\n" +
                     "Last Name: Smith\n" +
                     "Email: 123@gmail.com\n" +
                     "Home Address: 2 Cedar Way\n", conMan.readContact(1));
    }

    @Test
    public void canDeleteACreatedContact() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nSarah\nSmith\n234@gmail.com\n1 Cedar Way\n" +
                                                                        "1\nPriya\nPatil\n123@gmail.com\n2 Cedar Way\n" +
                                                                        "4\n1\n5\n").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertEquals("First Name: Priya\n" +
                     "Last Name: Patil\n" +
                     "Email: 123@gmail.com\n" +
                     "Home Address: 2 Cedar Way\n", conMan.readContact(1));
    }

    @Test
    public void ensuresAValidDigitIsEnteredWhenChoosingAnOption() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("a\nb\n1\n").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.optionSelected();
        assertTrue(recordedOutput.toString().contains("Please enter a valid number: "));
    }

    @Test
    public void conManLoopsThroughOptionsTillUserChoosesToExit() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nSarah\nSmith\n234@gmail.com\n1 Cedar Way\n" +
                                                                        "1\nPriya\nPatil\n123@gmail.com\n2 Cedar Way\n" +
                                                                        "4\n1\n5\n").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Delete a contact \n"));
    }

    @Test
    public void conManDisplaysPromptsToTheUserDuringTheMenuLoop() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("1\nSarah\nSmith\n234@gmail.com\n1 Cedar Way\n" +
                                                                        "1\nPriya\nPatil\n123@gmail.com\n2 Cedar Way\n5\n").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("1) Create a contact \n" +
                                                      "2) Read a contact's details \n" +
                                                      "3) Update a contact's details \n" +
                                                      "4) Delete a contact \n" +
                                                      "5) ExitConMan conMan.ConMan \n"));

    }
}
