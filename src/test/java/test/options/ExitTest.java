package test.options;

import conMan.ConMan;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.Option;
import org.junit.Test;
import test.FakeExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class ExitTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    @Test
    public void userEntering5ExitsConMan() {
        InputOutput console = new ConsoleIO(new ByteArrayInputStream("5\n".getBytes()), out);
        Option exitOption = new FakeExit(console);
        ConMan conMan = new ConMan(console, exitOption);
        conMan.optionSelected();
        assertTrue(recordedOutput.toString().contains("Exiting conMan.ConMan..."));
    }
}
