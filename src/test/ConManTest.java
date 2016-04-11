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
    InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("Priya\nPatil\n123@gmail.com\n" +
                                                                    "Sarah\nSmith\n234@gmail.com\n").getBytes()), out);
    ConMan conMan = new ConMan(consoleIO);

    @Test
    public void canReadCreatedContactsDetails() {
        conMan.create();
        conMan.create();
        assertEquals("First Name: Priya\n" +
                "Last Name: Patil\n" +
                "Email: 123@gmail.com\n", conMan.readContact(1));
        assertEquals("First Name: Sarah\n" +
                "Last Name: Smith\n" +
                "Email: 234@gmail.com\n", conMan.readContact(2));
    }

    @Test
    public void formatsTheNamesIntoANumberedList() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("Priya\nPatil\n123@gmail.com\n" +
                                                                        "Sarah\nSmith\n234@gmail.com\n").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO);
        conMan.create();
        conMan.create();
        assertEquals("1) Priya Patil\n" +
                     "2) Sarah Smith\n", conMan.listAllNames());
    }

    @Test
    public void readsContactInformationWhenSelected() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("Sarah\nSmith\n234@gmail.com\n".getBytes()), out);
        ConMan conMan = new ConMan(consoleIO);
        conMan.create();
        assertEquals("First Name: Sarah\n" +
                     "Last Name: Smith\n" +
                     "Email: 234@gmail.com\n", conMan.readContact(1));
    }

    @Test
    public void canUpdateTheFirstNameOfACreatedContact() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("Sarah\nSmith\n234@gmail.com\n" +
                                                                        "1\nSam\nSmith\n123@gmail.com\n").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO);
        conMan.create();
        conMan.update();
        assertEquals("First Name: Sam\n" +
                "Last Name: Smith\n" +
                "Email: 123@gmail.com\n", conMan.readContact(1));
    }

    @Test
    public void canDeleteACreatedContact() {
        InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream(("Sarah\nSmith\n234@gmail.com\n" +
                                                                        "Priya\nPatil\n123@gmail.com\n").getBytes()), out);
        ConMan conMan = new ConMan(consoleIO);
        conMan.create();
        conMan.create();
        conMan.delete(1);
        assertEquals("First Name: Priya\n" +
                "Last Name: Patil\n" +
                "Email: 123@gmail.com\n", conMan.readContact(1));
    }
}
