package main.contactfields;

import main.inputoutput.InputOutput;

public class Email implements Field {
    private final InputOutput console;
    private String email;

    public Email(InputOutput console) {
        this.console = console;
    }

    @Override
    public void set() {
        email = console.takeInput();
    }

    @Override
    public void update() {
        String userInput = console.takeInput();
        if (!userInput.isEmpty()) {
            email = userInput;
        }
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
