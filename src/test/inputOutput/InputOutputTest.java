package test.inputOutput;

import main.ConMan;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class InputOutputTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("\n".getBytes()), out);
    FakeIO userIO = new FakeIO(Arrays.asList("1"));


    @Test
    public void showsInitialGreetingToRecordedOutput() {
        ConMan conMan = new ConMan(consoleIO);
        conMan.showGreeting();
        assertTrue(recordedOutput.toString().contains("Welcome to ConMan!"));
    }
}