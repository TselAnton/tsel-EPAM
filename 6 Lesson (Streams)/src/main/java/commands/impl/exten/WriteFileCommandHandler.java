package commands.impl.exten;

import commands.impl.AbstractCommandHandler;
import exeptions.CommandArgumentsIncorrectExeption;
import exeptions.NotFoundFileException;
import exeptions.WriteFileExeption;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFileCommandHandler extends AbstractCommandHandler {
    private final String ADD_PARAMETR = "-a";
    private final String ERR_MESSAGE = "Ошибка записи в файл! ";

    public WriteFileCommandHandler() {
        super("[-a] \"path\" \"content\"",
                new String[]{"Запись в файл", "-a", "Запись в конец файла", "-path",
                    "Путь к файлу в двойных ковычках", "-content", "Записываемый текст"});
    }

    public void handle(String args) throws CommandArgumentsIncorrectExeption, NotFoundFileException, WriteFileExeption {
        try {
            boolean append = false;
            if (args.split(" ")[0].equals(ADD_PARAMETR)) {
                append = true;
            }
            writeInFile(args.split("\"")[1], args.split("\"")[3], append);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandArgumentsIncorrectExeption(ERR_MESSAGE + "Не верно указаны аргументы!");
        }
    }

    private void writeInFile(String filePath, String text, boolean writeToTheEnd) throws NotFoundFileException, WriteFileExeption {
        File file = new File(filePath);
        if (!file.exists()) throw new NotFoundFileException(ERR_MESSAGE + "Файла " + filePath + " не существует!");
        else {
            try (FileWriter fileWriter = new FileWriter(file, writeToTheEnd)){
                fileWriter.write(text);
                fileWriter.flush();
            } catch (IOException e) {
                throw new WriteFileExeption(ERR_MESSAGE + "Файл " + filePath + " не доступен для записи!");
            }
            System.out.println("Текст успешно записан в файл!");
        }
    }
}
