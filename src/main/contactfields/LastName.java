package main.contactfields;

import main.inputoutput.InputOutput;

public class LastName implements Field {
    private final InputOutput inputOutput;
    private String lastName;

    public LastName(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    @Override
    public void set() {
        lastName = inputOutput.takeInput();
    }

    @Override
    public String show() {
       return lastName;
    }

    public String showFieldName() {
        return "Last Name: ";
    }
}
