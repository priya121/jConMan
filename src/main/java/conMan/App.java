package conMan;

import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.ExitConMan;
import conMan.options.Option;


public class App {
    public static void main(String[] args) {
        InputOutput io = new ConsoleIO(System.in, System.out);
        Option exit = new ExitConMan(io);
        ConMan conMan = new ConMan(io, exit);
        conMan.menuLoop();
    }
}
