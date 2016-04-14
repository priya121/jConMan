package conMan.options;

import conMan.inputoutput.InputOutput;

public class ExitConMan implements Option, Exit {
    private InputOutput console;

    public ExitConMan(InputOutput console) {
        this.console = console;
    }

    @Override
    public void show() {
        console.showOutput("ExitConMan conMan.ConMan \n");
    }

    @Override
    public void perform() {
        System.exit(0);
    }
}
