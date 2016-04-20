package conMan;

import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;


public class App {
    public static void main(String[] args) {
        InputOutput io = new ConsoleIO(System.in, System.out);
        FileChooser fileChooser = new FileChooser(io);
        fileChooser.start();
    }
}
