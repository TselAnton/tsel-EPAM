package commands;

import commands.inter.Command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile implements Command {
    private final String arg = "[-a] \"path\" \"content\"";
    private final String description = "Запись в файл\n         -a — Запись в конец файла\n" +
            "         -path — Путь к файлу в двойных ковычках\n         -content — Записываемый текст";
    private final String ADD_PARAMETR = "-a";
    private final String ERR_MESSAGE = "Ошибка записи в файл! ";

    public void doCommand(String args) {
        boolean append = false;
        if (args.split(" ")[0].equals(ADD_PARAMETR)) {
            append = true;
        }
        try {
            writeInFile(args.split("\"")[1], args.split("\"")[3], append);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ERR_MESSAGE + "Не верно указаны аргументы!");
        }
    }

    private void writeInFile(String filePath, String text, boolean writeToTheEnd) {
        File file = new File(filePath);
        if (!file.exists()) System.out.println(ERR_MESSAGE + "Файла " + filePath + " не существует!");
        else {
            try {
                FileWriter fileWriter = new FileWriter(file, writeToTheEnd);
                fileWriter.write(text);
                fileWriter.flush();
            } catch (IOException e) {
                System.out.println(ERR_MESSAGE + "Файл " + filePath + " не доступен для записи!");
            }
            System.out.println("Текст успешно записан в файл!");
        }
    }

    public String getArgs() {
        return arg + "      " + description;
    }
}
