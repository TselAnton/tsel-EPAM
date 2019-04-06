package commands;

import commands.inter.Command;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CatFiles implements Command {
    private final String arg = "-path";
    private final String description = "Вывод содержимого файла в консоль\n         -path — Путь к файлу";
    private final String ERR_MESSAGE = "Ошибка чтения файла! ";

    public void doCommand(String filePath) {
        try {
            filePath = filePath.replaceAll("\"", "");
            StringBuilder text = new StringBuilder();
            FileReader fileReader = new FileReader(filePath);
            int byteChar;
            while ((byteChar = fileReader.read()) != -1) {
                text.append((char) byteChar);
            }
            System.out.println("Файл " + filePath + ":\n" + text + "\n");
        } catch (FileNotFoundException e) {
            System.out.println(ERR_MESSAGE + "Файл не найден!");
        } catch (IOException e) {
            System.out.println(ERR_MESSAGE + "Файл не может быть прочитан!");
        }
    }

    public String getArgs() {
        return arg + "      " + description;
    }
}
