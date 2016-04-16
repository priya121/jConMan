package test.options;

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

public class ExitTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    ContactList allContacts = new ContactList();

    @Test
    public void userEntering5ExitsConMan() throws IOException {
        File output = temporaryFolder.newFile("output.txt");
        InputOutput console = new ConsoleIO(new ByteArrayInputStream("5\n".getBytes()), out);
        Option exitOption = new FakeExit(console, allContacts, output);
        ConMan conMan = new ConMan(console, exitOption, output,allContacts );
        conMan.optionSelected();
        assertTrue(recordedOutput.toString().contains("Exiting ConMan..."));
    }
}
