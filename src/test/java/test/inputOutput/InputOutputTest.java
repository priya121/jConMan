package test.inputOutput;

import conMan.ConMan;
import conMan.ContactList;
import fileTypes.FileType;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.Option;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import test.FakeExit;
import file.FakeFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

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
        ContactList contactList = new ContactList();
        FileType fakeFile = new FakeFile(allContacts, contactList);
        Option exitOption = new FakeExit(consoleIO, fakeFile);
        ConMan conMan = new ConMan(consoleIO, exitOption, fakeFile, allContacts);
        conMan.showGreeting();
        assertTrue(recordedOutput.toString().contains("Welcome to ConMan!"));
    }
}