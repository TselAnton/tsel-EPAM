package commands;

import commands.inter.Command;
import exeptions.CreateFileException;

import java.io.*;

public class CopyPath implements Command {
    private final String arg = "[-a] \"source_path\" \"dest_path\"";
    private final String description = "Копирование содержимого одного файла в другой\n         " +
            "-a — Запись в конец файла\n         " +
            "-souece_path — Путь к исходному файлу в двойных ковычках\n         " +
            "-dest_path — Путь к перезаписываемому файлу в двойных ковычках";
    private final String ADD_PARAMETR = "-a";
    private final String ERR_MESSAGE = "Ошибка копирования файла! ";

    public void doCommand(String args) {
        boolean append = false;
        if (args.split(" ")[0].equals(ADD_PARAMETR)) {
            append = true;
        }
        try {
            copyFile(args.split("\"")[1], args.split("\"")[3], append);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ERR_MESSAGE + "Не верно указаны аргументы!");
        }
    }

    private void copyFile(String pathOut, String pathIn, boolean append) {
        try {
            StringBuilder text = new StringBuilder();
            FileReader fileReader = new FileReader(pathOut);
            int byteChar;
            while ((byteChar = fileReader.read()) != -1) {
                text.append((char)byteChar);
            }
            File fileIn = new File(pathIn);
            if (!fileIn.exists()) {
                boolean newFile = fileIn.createNewFile();
                if (!newFile) throw new CreateFileException(ERR_MESSAGE + "Не удалось создать файл-копию!");
            }
            FileWriter fileWriter = new FileWriter(fileIn, append);
            System.out.println("Исходный текст: " + text.toString());
            fileWriter.write(text.toString());
            fileWriter.flush();
            System.out.println("Файл " + pathOut + " был успешно копирован в " + pathIn + "!");
        } catch (FileNotFoundException e) {
            System.out.println(ERR_MESSAGE + "Исходный файл не найден!");
        } catch (IOException e) {
            System.out.println(ERR_MESSAGE + "Ошибка прочтения исходного файла!");
        } catch (CreateFileException createFileException) {
            System.out.println(createFileException.getMessage());
        }
    }

    public String getArgs() {
        return arg + "      " + description;
    }
}
