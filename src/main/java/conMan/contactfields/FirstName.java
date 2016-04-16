package conMan.contactfields;

import conMan.inputoutput.InputOutput;

public class FirstName implements Field {
    private InputOutput console;
    private String firstName;

    public FirstName(InputOutput console) {
        this.console = console;
    }

    @Override
    public void set() {
       firstName = console.takeInput();
    }

    @Override
    public void update() {
        String userInput = console.takeInput();
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
