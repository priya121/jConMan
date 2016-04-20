package conMan;

import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import conMan.options.ExitConMan;
import conMan.options.Option;
import fileTypes.CSVFile;
import fileTypes.FileType;
import fileTypes.JSONFile;

import java.io.File;


public class App {
    public static void main(String[] args) {
        InputOutput io = new ConsoleIO(System.in, System.out);
        ContactList list = new ContactList();
        File file = new File("/Users/priyapatil/Work/MOCK_DATA.csv");
        FileType CSV = new CSVFile(file, io, list);
        FileType jsonFile = new JSONFile(file, io, list);
        Option exit = new ExitConMan(io, CSV);
        ConMan conMan = new ConMan(io, exit, CSV, list);
        conMan.menuLoop();
    }
}
