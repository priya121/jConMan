package conMan.inputoutput;

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
        output.print(message);
        return null;
    }

    @Override
    public String takeInput() {
        try {
            return input.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearScreen() {
        output.print("\033[2J\033[1;1H");
    }
}
