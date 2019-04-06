package controller;

import commands.*;
import commands.inter.Command;

import java.util.HashMap;

public class CommandController {
    private HashMap<String, Command> commands = new HashMap<>();

    public CommandController() {
        commands.put("createFile", new CreateFile());
        commands.put("writeFile", new WriteFile());
        commands.put("cp", new CopyPath());
        commands.put("deleteFile", new DeleteFile());
        commands.put("zip", new ZipFiles());
        commands.put("cat", new CatFiles());
        commands.put("exit", new ExitFromApp());
    }

    public void getCommand(String args) {
        if (args.equals("help")) showAllCommands();
        else {
            String[] arguments = args.split(" ");
            if (commands.containsKey(arguments[0])) {
                commands.get(arguments[0]).doCommand(args.replaceAll(arguments[0] + " ", ""));
            } else {
                System.out.println("Команда " + args + " не найдена!");
            }
        }
    }

    private void showAllCommands() {
        System.out.println("Все доступные комманды:");
        System.out.println("    help        Вывод всех существующих комманд\n");
        for (String command : commands.keySet()) {
            System.out.println("    " + command + " " + commands.get(command).getArgs() + "\n");
        }
    }
}
