package main.inputoutput;

import java.io.*;


public class ConsoleIO implements InputOutput {
    BufferedReader input;
    PrintStream output;

    public ConsoleIO(InputStream input, PrintStream output) {
        this.input = new BufferedReader(new InputStreamReader(input));
        this.output = output;
    }

    @Override
    public String showOutput(String message) {
        output.println(message);
        return null;
    }

}
