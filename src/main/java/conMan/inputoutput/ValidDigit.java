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

    public int get(int size) {
        String userInput = console.takeInput();
        while (!check(userInput) || !choiceExists(userInput, size)) {
            console.showOutput("Please enter a valid number: \n");
            userInput = console.takeInput();
        }
        return Integer.parseInt(userInput);
    }

    private boolean choiceExists(String userInput, int size) {
        return Integer.parseInt(userInput) <= size;
    }
}
