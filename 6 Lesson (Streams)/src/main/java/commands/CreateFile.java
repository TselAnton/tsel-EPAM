package commands;

import commands.inter.Command;

import java.io.File;
import java.io.IOException;

public class CreateFile implements Command {
    private final String arg = "-path";
    private final String description = "Создание файла\n          -path — Путь к файлу";
    private final String ERR_MESSAGE = "Ошибка создания файла! ";

    public void doCommand(String filePath) {
        filePath = filePath.replaceAll("\"","");
        File file = new File(filePath);
        try {
            boolean newFile = file.createNewFile();
            if (newFile) System.out.println("Файл " + filePath + " успешно создан!");
            else System.out.println(ERR_MESSAGE + "Файл " + filePath + " уже существует!");
        } catch (IOException e) {
            System.out.println(ERR_MESSAGE + "Неверно указан путь!");
        }
    }

    public String getArgs() {
        return arg + "      " + description;
    }
}
