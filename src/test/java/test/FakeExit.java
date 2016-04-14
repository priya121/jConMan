package test;

import conMan.inputoutput.InputOutput;
import conMan.options.Exit;
import conMan.options.Option;

public class FakeExit implements Option, Exit {
    private InputOutput inputOutput;


    public FakeExit(InputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    @Override
    public void show() {
        inputOutput.showOutput("ExitConMan conMan.ConMan \n");
    }

    @Override
    public void perform() {
        inputOutput.showOutput("Exiting ConMan...");
    }
}
