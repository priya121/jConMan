package main.contactfields;

import main.inputoutput.InputOutput;

public class HomeAddress implements Field {
    private final InputOutput inputOutput;
    private String homeAddress;


    public HomeAddress(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    @Override
    public void set() {
        homeAddress = inputOutput.takeInput();
    }

    @Override
    public String show() {
        return homeAddress;
    }

    @Override
    public String showFieldName() {
        return "Home Address: ";
    }
}
