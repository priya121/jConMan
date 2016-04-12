package main.inputoutput;

public class ValidDigit {

    public boolean check(String userInput) {
        try {
            Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
