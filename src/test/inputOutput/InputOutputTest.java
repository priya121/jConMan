package test.inputOutput;

import main.ConMan;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InputOutputTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);
    InputOutput consoleIO = new ConsoleIO(new ByteArrayInputStream("\n".getBytes()), out);
    InputOutput userIO = new FakeIO();

   @Test
   public void showsInitialGreeting() {
       ConMan conMan = new ConMan(userIO);
       assertEquals("Welcome to ConMan!", conMan.showGreeting());
   }

    @Test
    public void showsInitialGreetingToRecordedOutput() {
        ConMan conMan = new ConMan(consoleIO);
        conMan.showGreeting();
        assertTrue(recordedOutput.toString().contains("Welcome to ConMan!"));
    }
}