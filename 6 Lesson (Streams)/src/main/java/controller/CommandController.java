package controller;

import commands.CommandHandler;
import commands.impl.exten.*;
import exeptions.*;

import java.util.HashMap;

public class CommandController {
    private HashMap<String, CommandHandler> commands = new HashMap<>();

    public CommandController() {
        commands.put("createFile", new CreateFileCommandHandler());
        commands.put("writeFile", new WriteFileCommandHandler());
        commands.put("cp", new CopyPathCommandHandler());
        commands.put("deleteFile", new DeleteFileCommandHandler());
        commands.put("zip", new ZipFilesCommandHandler());
        commands.put("cat", new CatFilesCommandHandler());
        commands.put("exit", new ExitFromAppCommandHandler());
    }

    public void getCommand(String args) {
        if (args.equals("help")) {
            showAllCommands();
        }
        else {
            String[] arguments = args.split(" ");
            try {
                if (commands.containsKey(arguments[0])) {
                    commands.get(arguments[0]).handle(args.replaceAll(arguments[0] + " ", ""));
                } else {
                    System.out.println("Команда " + args + " не найдена!");
                }
            } catch (CommandArgumentsIncorrectExeption commandArgumentsIncorrectExeption) {
                commandArgumentsIncorrectExeption.printStackTrace();
            } catch (PathCorrectExeption pathCorrectExeption) {
                pathCorrectExeption.printStackTrace();
            } catch (NotFoundFileException e) {
                e.printStackTrace();
            } catch (CreateFileException e) {
                e.printStackTrace();
            } catch (DeleteFileExeption deleteFileExeption) {
                deleteFileExeption.printStackTrace();
            } catch (ReadFileException e) {
                e.printStackTrace();
            } catch (WriteFileExeption writeFileExeption) {
                writeFileExeption.printStackTrace();
            }
        }
    }

    private void showAllCommands() {
        System.out.println("Все доступные комманды:");
        for (String command : commands.keySet()) {
            System.out.printf("%4s%s %s\n", " ", command, commands.get(command).getDescription());
        }
    }
}
