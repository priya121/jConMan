package conMan.contactfields;

import conMan.inputoutput.InputOutput;

public class HomeAddress implements Field {
    private InputOutput console;
    private String homeAddress;
    private String savedValue;

    public HomeAddress(InputOutput console) {
        this.console = console;
    }

    public HomeAddress(String savedValue, InputOutput console) {
        this.savedValue = savedValue;
        this.console = console;
    }

        @Override
    public void set() {
        homeAddress = console.takeInput();
    }

    @Override
    public void update() {
        String userInput = console.takeInput();
        if (!userInput.isEmpty()) {
            homeAddress = userInput;
        }
    }

    @Override
    public String get() {
        return homeAddress;
    }

    @Override
    public String showFieldName() {
        return "Home Address: ";
    }

    public void setExisting() {
        homeAddress = savedValue;
    }
}
