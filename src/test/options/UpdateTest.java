package test.options;

import main.ConMan;
import main.contactfields.Contact;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import main.options.Option;
import main.options.Update;
import org.junit.Test;
import test.FakeExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class UpdateTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    @Test
    public void updateHasAUpdateTitle() {
        Contact maya = createContact("Maya\nPatil\n123@gmail.com\n5 Rosebury Av\n");
        Contact sarah = createContact("Sarah\nSmith\n234@gmail.com\n6 Rosebury Av\n");
        Update update = new Update(Arrays.asList(maya, sarah), input("3\n1\n"));
        update.show();
        assertTrue(recordedOutput.toString().contains("Update a contact's details \n"));
    }

    @Test
    public void userEntering3ShowsUpdateAContactTitle() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                      "3\n1\nSarah\nSmith\n234@gmail.com\n2 Cedar Way\n" +
                                      "5\n");
        Option exitOption = new FakeExit(consoleIO);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Update a contact's details \n"));
    }

    @Test
    public void userCanUpdateAChosenContactsDetails() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                      "3\n1\nSam\nPatil\n789@gmail.com\n3 Cedar Way\n" +
                                      "2\n1\n5\n");
        Option exitOption = new FakeExit(consoleIO);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("First Name: Sam\n" +
                                                      "Last Name: Patil\n" +
                                                      "Email: 789@gmail.com\n" +
                                                      "Home Address: 3 Cedar Way\n"));
    }

    @Test
    public void userMustEnterAValidNumberWhenChoosingAContact() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                      "3\nabc\n1\nSam\nPatil\n789@gmail.com\n3 Cedar Way\n5\n");
        Option exitOption = new FakeExit(consoleIO);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Please enter a valid number: "));
    }

    @Test
    public void userCanReadListOfContactsBeforeDecidingWhichToUpdate() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                      "1\nMaya\nPatil\n345@gmail.com\n2 Cedar Way\n" +
                                      "3\n1\nBen\nSmith\n123@gmail.com\n3 Cedar Way\n" +
                                      "5\n");
        Option exitOption = new FakeExit(consoleIO);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("1) Priya Patil\n" +
                                                      "2) Maya Patil\n"));
    }
    
    @Test
    public void userPromptThatUserCanLeaveFieldBlank() {
        InputOutput consoleIO = input("1\nMaya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                      "3\n1\nSarah\nSmith\n234@gmail.com\n2 Cedar Way\n" +
                                      "5\n");
        Option exitOption = new FakeExit(consoleIO);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("Fill in field to update or leave blank to keep previous value: \n"));
    }

    @Test
    public void ifUserLeavesFieldBlankPreviousValueRemains() {
        InputOutput consoleIO = input("1\nPriya\nPatil\n123@gmail.com\n1 Cedar Way\n" +
                                      "3\n1\n\n\n\n3 Cedar Way\n" +
                                      "2\n1\n" +
                                      "5\n");
        Option exitOption = new FakeExit(consoleIO);
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.menuLoop();
        assertTrue(recordedOutput.toString().contains("First Name: Priya\n" +
                                                      "Last Name: Patil\n" +
                                                      "Email: 123@gmail.com\n" +
                                                      "Home Address: 3 Cedar Way\n"));
    }

    private Contact createContact(String input) {
        ConsoleIO console = new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
        return new Contact(console);
    }

    private InputOutput input(String input) {
        return new ConsoleIO(new ByteArrayInputStream(input.getBytes()), out);
    }
}
