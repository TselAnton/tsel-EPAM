package commands.impl;

import commands.CommandHandler;

public abstract class AbstractCommandHandler implements CommandHandler {
    private String argument;
    private String[] description;

    public AbstractCommandHandler(String arg, String[] desc) {
        this.argument = arg;
        this.description = desc;
    }

    @Override
    public String getDescription() {
        String desc =  String.format("%s%3s—%3s%s\n", argument, " ", " ", description[0]);
        for (int i = 1; i < description.length; i = i + 2) {
            desc += String.format("%7s%s — %s\n", " ",description[i],description[i + 1]);
        }
        return desc;
    }
}
