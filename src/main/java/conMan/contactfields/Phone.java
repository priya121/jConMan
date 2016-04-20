package conMan.contactfields;

import conMan.inputoutput.InputOutput;

public class Phone implements Field {
    private InputOutput console;
    private String phone;
    private String savedvalue;

    public Phone(InputOutput console) {
        this.console = console;
    }

    public Phone(String savedValue, InputOutput console) {
        this.savedvalue = savedValue;
        this.console  = console;
    }

    @Override
    public void set() {
        phone = console.takeInput();
    }

    @Override
    public void update() {
        String userInput = console.takeInput();
        if (!userInput.isEmpty()) {
            phone = userInput;
        }
    }

    @Override
    public void setExisting() {
        phone = savedvalue;
    }

    @Override
    public String get() {
        return phone;
    }

    @Override
    public String showFieldName() {
        return "Phone Number: ";
    }
}
