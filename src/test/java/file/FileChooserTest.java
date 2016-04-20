package file;

import conMan.FileChooser;
import conMan.inputoutput.ConsoleIO;
import conMan.inputoutput.InputOutput;
import fileTypes.CSVFile;
import fileTypes.JSONFile;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class FileChooserTest {
    ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(recordedOutput);

    @Test
    public void displaysFileTypeToUser() {
        InputOutput console = new ConsoleIO(new ByteArrayInputStream("1\n".getBytes()), out);
        FileChooser fileChooser = new FileChooser(console);
        fileChooser.choose();
        assertTrue(recordedOutput.toString().contains("Choose a file type: \n" +
                                                      "1) Write to your own JSON File\n" +
                                                      "2) Write to your own CSV File\n" +
                                                      "3) Import 1000 Dummy CSV Contacts\n"));
    }

    @Test
    public void userEntering1ReturnsAnEmptyJSONFile() {
        InputOutput console = new ConsoleIO(new ByteArrayInputStream("1\n".getBytes()), out);
        FileChooser fileChooser = new FileChooser(console);
        assertTrue(fileChooser.choose() instanceof JSONFile);
    }

    @Test
    public void userEntering2ReturnsAnEmptyCSVFile() {
        InputOutput console = new ConsoleIO(new ByteArrayInputStream("2\n".getBytes()), out);
        FileChooser fileChooser = new FileChooser(console);
        assertTrue(fileChooser.choose() instanceof CSVFile);
    }

    @Test
    public void userEntering3ReturnsACSVFile() {
        InputOutput console = new ConsoleIO(new ByteArrayInputStream("3\n".getBytes()), out);
        FileChooser fileChooser = new FileChooser(console);
        assertTrue(fileChooser.choose() instanceof CSVFile);
    }

}
