package test.options;

import main.ConMan;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import main.options.Option;
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
        assertTrue(recordedOutput.toString().contains("Exiting ConMan..."));
    }
}
