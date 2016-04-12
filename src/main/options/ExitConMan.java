package main.options;

import main.inputoutput.InputOutput;

public class ExitConMan implements Option, Exit {
    private InputOutput inputOutput;

    public ExitConMan(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }
    @Override
    public void show() {
        inputOutput.showOutput("ExitConMan ConMan \n");
    }

    @Override
    public void perform() {
        System.exit(0);
    }
}
