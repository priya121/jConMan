package conMan.contactfields;

import conMan.inputoutput.InputOutput;

public class Email implements Field {
    private InputOutput console;
    private String email;
    private String savedValue;

    public Email(InputOutput console) {
        this.console = console;
    }

    public Email(String savedValue, InputOutput console) {
        this.savedValue = savedValue;
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

    public void setExisting() {
        email = savedValue;
    }

}
