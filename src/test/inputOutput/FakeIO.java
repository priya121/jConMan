package test.inputOutput;

import main.inputoutput.InputOutput;

public class FakeIO implements InputOutput {

    @Override
    public String showOutput(String message) {
        return message;
    }
}