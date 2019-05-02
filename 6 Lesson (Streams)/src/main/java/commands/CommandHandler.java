package commands;

import exeptions.*;

public interface CommandHandler {
    void handle(String args) throws NotFoundFileException, ReadFileException, CommandArgumentsIncorrectExeption, CreateFileException, PathCorrectExeption, DeleteFileExeption, WriteFileExeption;
    String getDescription();
}
