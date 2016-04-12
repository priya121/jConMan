package test.inputOutput;

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

public class InputOutputTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("\n".getBytes()), out);
    Option exitOption = new FakeExit(consoleIO);

    @Test
    public void showsInitialGreetingToRecordedOutput() {
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.showGreeting();
        assertTrue(recordedOutput.toString().contains("Welcome to ConMan!"));
    }
}