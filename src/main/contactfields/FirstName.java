package main.contactfields;

import main.inputoutput.InputOutput;

public class FirstName implements Field {
    private final InputOutput inputOutput;
    private String firstName;

    public FirstName(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    @Override
    public void set() {
       firstName = inputOutput.takeInput();
    }

    @Override
    public String show() {
        return firstName;
    }

    public String showFieldName() {
        return "First Name: ";
    }

}
