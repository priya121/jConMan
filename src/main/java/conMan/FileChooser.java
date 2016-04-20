package conMan;

import conMan.inputoutput.InputOutput;
import conMan.inputoutput.ValidDigit;
import conMan.options.ExitConMan;
import conMan.options.Option;
import fileTypes.CSVFile;
import fileTypes.FileType;
import fileTypes.JSONFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileChooser {
    private final List<FileType> fileTypes;
    private final ContactList list;
    private InputOutput console;
    private FileType chosenFile;

    public FileChooser(InputOutput console) {
        this.console = console;
        this.list = new ContactList();
        File emptyJson = new File("Empty.json");
        File emptyCSV = new File("Empty.csv");
        File CSVPath = new File("1000Contacts.csv");
        FileType JSON = new JSONFile(emptyJson, console, list);
        FileType CSV = new CSVFile(emptyCSV, console, list);
        FileType dummyCSV = new CSVFile(CSVPath, console, list);
        this.fileTypes = Arrays.asList(JSON, CSV, dummyCSV);
    }

    public FileType choose() {
        displayChoices();
        ValidDigit validDigit = new ValidDigit(console);
        int chosenOption = validDigit.get(fileTypes.size());
        chosenFile = fileTypes.get(chosenOption - 1);
        return chosenFile;
    }

    public void start() {
        choose();
        Option exit = new ExitConMan(console, chosenFile);
        new ConMan(console, exit, chosenFile, list).menuLoop();
    }
    private void displayChoices() {
        console.showOutput("Choose a file type: \n" +
                           "1) Write to your own JSON File\n" +
                           "2) Write to your own CSV File\n" +
                           "3) Import 1000 Dummy CSV Contacts\n");
    }
}
