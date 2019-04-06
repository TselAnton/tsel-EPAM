package commands;

import commands.inter.Command;

public class ExitFromApp implements Command {

    private final String description = "Выход из программы";

    public void doCommand(String args) {
        System.exit(0);
    }

    public String getArgs() {
        return "        " + description;
    }
}
