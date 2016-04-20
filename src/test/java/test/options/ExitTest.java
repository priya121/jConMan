package test.options;

import conMan.ConMan;
import conMan.ContactList;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import fileTypes.FileType;
import org.junit.Test;
import test.FakeExit;
import file.FakeFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class ExitTest {

    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    ContactList allContacts = new ContactList();
    ContactList contactList = new ContactList();

    @Test
    public void userEntering5ExitsConMan() throws IOException {
        InputOutput console = new ConsoleIO(new ByteArrayInputStream("5\n".getBytes()), out);
        FileType fakeFile = new FakeFile(allContacts, contactList);
        FakeExit exitOption = new FakeExit(console, fakeFile);
        ConMan conMan = new ConMan(console, exitOption, fakeFile, allContacts);
        conMan.menuChoice();
        assertTrue(recordedOutput.toString().contains("Exiting ConMan..."));
    }
}
