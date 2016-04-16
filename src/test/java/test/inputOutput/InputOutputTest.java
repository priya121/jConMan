package test.inputOutput;

import conMan.ConMan;
import conMan.ContactList;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.Option;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import test.FakeExit;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class InputOutputTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    ContactList allContacts = new ContactList();
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("\n".getBytes()), out);

    @Test
    public void showsInitialGreetingToRecordedOutput() throws IOException {
        File output = temporaryFolder.newFile("output.txt");
        Option exitOption = new FakeExit(consoleIO, allContacts, output);
        ConMan conMan = new ConMan(consoleIO, exitOption, output, allContacts);
        conMan.showGreeting();
        assertTrue(recordedOutput.toString().contains("Welcome to ConMan!"));
    }
}