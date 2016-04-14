package test.inputOutput;

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

public class InputOutputTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("\n".getBytes()), out);
    Option exitOption = new FakeExit(consoleIO);

    @Test
    public void showsInitialGreetingToRecordedOutput() {
        ConMan conMan = new ConMan(consoleIO, exitOption);
        conMan.showGreeting();
        assertTrue(recordedOutput.toString().contains("Welcome to conMan.ConMan!"));
    }
}