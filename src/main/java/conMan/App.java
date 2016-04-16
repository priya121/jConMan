package conMan;

import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.ExitConMan;
import conMan.options.Option;

import java.io.File;


public class App {
    public static void main(String[] args) {
        InputOutput io = new ConsoleIO(System.in, System.out);
        ContactList list = new ContactList();
        File file = new File("/Users/priyapatil/Work/conMan.json");
        Option exit = new ExitConMan(io, list, file);
        ConMan conMan = new ConMan(io, exit, file, list);
        conMan.menuLoop();
    }
}
