package conMan.contactfields;

import conMan.inputoutput.InputOutput;

public class LastName implements Field {
    private InputOutput console;
    private String lastName;

    public LastName(InputOutput console) {
        this.console = console;
    }

    @Override
    public void set() {
        lastName = console.takeInput();
    }

    @Override
    public void update() {
        String userInput = console.takeInput();
        if (!userInput.isEmpty()) {
            lastName = userInput;
        }
    }

    @Override
    public String show() {
       return lastName;
    }

    public String showFieldName() {
        return "Last Name: ";
    }

}
