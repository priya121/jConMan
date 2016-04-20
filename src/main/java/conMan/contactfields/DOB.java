package conMan.contactfields;

import conMan.inputoutput.InputOutput;

public class DOB implements Field {
    private String dob;
    private InputOutput console;
    private String savedValue;

    public DOB(InputOutput console) {
    this.console = console;
   }

    public DOB(String savedValue, InputOutput console) {
        this.savedValue = savedValue;
        this.console = console;
    }

    @Override
    public void set() {
        dob = console.takeInput();
    }

    @Override
    public void update() {
        String userInput = this.console.takeInput();
        if (!userInput.isEmpty()) {
            dob = userInput;
        }
    }

    @Override
    public void setExisting() {
        dob = savedValue;
    }

    @Override
    public String get() {
        return dob;
    }

    @Override
    public String showFieldName() {
        return "D.O.B: ";
    }
}
