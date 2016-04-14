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

    public int takeChar() {
        try {
            return input.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
