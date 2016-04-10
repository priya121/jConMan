package test;

import main.ConMan;
import main.ConsoleIO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsoleIOTest {
    private ConsoleIO console = new FakeIO();
    ConMan conMan = new ConMan(console);

   @Test
   public void showsInitialGreeting() {
       assertEquals("Welcome to ConMan!", conMan.showGreeting());
   }

}