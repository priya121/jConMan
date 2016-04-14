package main.contactfields;

import main.inputoutput.InputOutput;

public class FirstName implements Field {
    private final InputOutput console;
    private String firstName;
    private String userInput;

    public FirstName(InputOutput console) {
        this.console = console;
    }

    @Override
    public void set() {
       firstName = console.takeInput();
    }

    @Override
    public void update() {
        userInput = console.takeInput();
        if (!userInput.isEmpty()) {
            firstName = userInput;
        }
    }

    @Override
    public String show() {
        return firstName;
    }

    public String showFieldName() {
        return "First Name: ";
    }
}
