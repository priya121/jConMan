package test;

import main.ConsoleIO;

public class FakeIO implements ConsoleIO {

    @Override
    public String showOutput(String message) {
        return message;
    }
}