package conMan.contactfields;

import conMan.inputoutput.InputOutput;

public class FirstName implements Field {
    private String firstName;
    private String savedValue;
    private InputOutput console;

    public FirstName(InputOutput console) {
        this.console = console;
    }

    public FirstName(String savedValue) {
        this.savedValue = savedValue;
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

    @Override
    public String showFieldName() {
        return "First Name: ";
    }

    @Override
    public void setExisting() {
        firstName = savedValue;
    }
}
