package conMan;

import conMan.inputoutput.InputOutput;
import conMan.inputoutput.ValidDigit;
import conMan.options.ExitConMan;
import conMan.options.Option;
import filetypes.CSVFile;
import filetypes.FileType;
import filetypes.JSONFile;

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
        File ThousandCSV = new File("1000Contacts.csv");
        FileType JSON = new JSONFile(emptyJson, console, list);
        FileType CSV = new CSVFile(emptyCSV, console, list);
        FileType dummyCSV = new CSVFile(ThousandCSV, console, list);
        this.fileTypes = Arrays.asList(JSON, CSV, dummyCSV);
    }

    public void start() {
        choose();
        Option exit = new ExitConMan(console, chosenFile);
        console.clearScreen();
        new ConMan(console, exit, chosenFile, list).menuLoop();
    }

    public FileType choose() {
        displayChoices();
        ValidDigit validDigit = new ValidDigit(console);
        int chosenOption = validDigit.get(fileTypes.size() + 1);
        if (userFilePath(chosenOption)) return chosenFile;
        chosenFile = fileTypes.get(chosenOption - 1);
        return chosenFile;
    }

    private boolean userFilePath(int chosenOption) {
        if (chosenOption == 4) {
            console.showOutput("Enter the file path you would like to import contacts from: ");
            File emptyCSV = new File(console.takeInput());
            chosenFile = new CSVFile(emptyCSV, console, list);
            return true;
        }
        return false;
    }

    public void displayChoices() {
        console.showOutput("Choose a file type: \n" +
                           "1) Write to a JSON File\n" +
                           "2) Write to a CSV File\n" +
                           "3) Import 1000 Dummy CSV Contacts\n" +
                           "4) Import from your own CSV file location\n");
    }
}
