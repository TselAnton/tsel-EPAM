package commands.impl.exten;

import commands.impl.AbstractCommandHandler;

public class ExitFromAppCommandHandler extends AbstractCommandHandler {

    public ExitFromAppCommandHandler() {
        super("", new String[]{"Выход из программы"});
    }

    public void handle(String args) {
        System.exit(0);
    }
}
