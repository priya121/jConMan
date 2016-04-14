import main.ConMan;
import main.inputoutput.ConsoleIO;
import main.inputoutput.InputOutput;
import main.options.ExitConMan;
import main.options.Option;

public class App {
    public static void main(String[] args) {
        InputOutput io = new ConsoleIO(System.in, System.out);
        Option exit = new ExitConMan(io);
        ConMan conMan = new ConMan(io, exit);
        conMan.menuLoop();
    }
}
