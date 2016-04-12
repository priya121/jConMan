package test;

import main.inputoutput.InputOutput;
import main.options.Exit;
import main.options.Option;

public class FakeExit implements Option, Exit {
    private InputOutput inputOutput;


    public FakeExit(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    @Override
    public void show() {
        inputOutput.showOutput("ExitConMan ConMan \n");
    }

    @Override
    public void perform() {
        inputOutput.showOutput("Exiting ConMan...");
    }
}
