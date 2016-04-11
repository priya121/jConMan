package main.contactfields;

import main.inputoutput.InputOutput;

public class Email implements Field {
    private final InputOutput inputOutput;
    private String email;


    public Email(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    @Override
    public void set() {
        email = inputOutput.takeInput();
    }

    @Override
    public String show() {
        return email;
    }

    @Override
    public String showFieldName() {
        return "Email: ";
    }
}
