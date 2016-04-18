package conMan.contactfields;

import conMan.inputoutput.InputOutput;

public class LastName implements Field {
    private InputOutput console;
    private String lastName;
    private String savedValue;

    public LastName(InputOutput console) {
        this.console = console;
    }

    public LastName(String savedValue, InputOutput console) {
        this.savedValue = savedValue;
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
    public String get() {
       return lastName;
    }

   @Override
    public String showFieldName() {
        return "Last Name: ";
    }

    public void setExisting() {
        lastName = savedValue;
    }
}
