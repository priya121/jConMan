package test.inputOutput;

import main.inputoutput.InputOutput;

import java.util.LinkedList;
import java.util.List;

public class FakeIO implements InputOutput {

    private final LinkedList<String> input;

    public FakeIO(List<String> input) {
        this.input = new LinkedList<>(input);
    }

    @Override
    public String takeInput() {
        return input.pop();
    }


    @Override
    public String showOutput(String message) {
        return message;
    }

    @Override
    public int takeChar() {
        return input.pop().charAt(0);
    }
}