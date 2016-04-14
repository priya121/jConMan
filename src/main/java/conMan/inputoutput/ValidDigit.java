package conMan.inputoutput;

public class ValidDigit {
    private final InputOutput console;

    public ValidDigit(InputOutput console) {
        this.console = console;
    }

    private boolean check(String userInput) {
        try {
            Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getValidDigit() {
        String userInput = console.takeInput();
        while (!check(userInput)) {
            console.showOutput("Please enter a valid number: ");
            userInput = console.takeInput();
        }
        return Integer.parseInt(userInput);
    }
}
