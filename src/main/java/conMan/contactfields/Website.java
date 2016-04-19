package conMan.contactfields;

import conMan.inputoutput.InputOutput;

public class Website implements Field {
    private InputOutput console;
    private String website;
    private String savedValue;

    public Website(InputOutput console) {
        this.console = console;
    }

    public Website(String savedValue, InputOutput console) {
        this.savedValue = savedValue;
        this.console = console;
    }

    @Override
    public void set() {
        website = console.takeInput();
    }

    @Override
    public void update() {
        String userInput = console.takeInput();
        if (!userInput.isEmpty()) {
            website = userInput;
        }
    }

    @Override
    public void setExisting() {
        website = savedValue;
    }

    @Override
    public String get() {
        return website;
    }

    @Override
    public String showFieldName() {
        return "Website: ";
    }
}
