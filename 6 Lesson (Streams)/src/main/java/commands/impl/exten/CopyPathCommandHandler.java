package commands.impl.exten;

import commands.impl.AbstractCommandHandler;
import exeptions.CommandArgumentsIncorrectExeption;
import exeptions.CreateFileException;
import exeptions.NotFoundFileException;
import exeptions.ReadFileException;

import java.io.*;

public class CopyPathCommandHandler extends AbstractCommandHandler {
    private final String ADD_PARAMETR = "-a";
    private final String ERR_MESSAGE = "Ошибка копирования файла! ";

    public CopyPathCommandHandler() {
        super("[-a] \"source_path\" \"dest_path\"",
                new String[]{"Копирование файла", "-a", "Запись в конец файла", "-souece_path",
                        "Путь к исходному файлу в двойных ковычках", "-dest_path", "Путь к перезаписываемому файлу в двойных ковычках"});
    }

    public void handle(String args) throws CommandArgumentsIncorrectExeption, NotFoundFileException, ReadFileException, CreateFileException {
        try {
            boolean append = false;
            if (args.split(" ")[0].equals(ADD_PARAMETR)) {
                append = true;
            }
            copyFile(args.split("\"")[1], args.split("\"")[3], append);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandArgumentsIncorrectExeption(ERR_MESSAGE + "Не верно указаны аргументы!");
        }
    }

    private void copyFile(String pathOut, String pathIn, boolean append) throws NotFoundFileException, ReadFileException, CreateFileException {
        try (FileReader fileReader = new FileReader(pathOut)){
            StringBuilder text = new StringBuilder();
            int byteChar;
            while ((byteChar = fileReader.read()) != -1) {
                text.append((char)byteChar);
            }
            File fileIn = new File(pathIn);
            if (!fileIn.exists()) {
                boolean newFile = fileIn.createNewFile();
                if (!newFile) throw new CreateFileException(ERR_MESSAGE + "Не удалось создать файл-копию!");
            }
            try (FileWriter fileWriter = new FileWriter(fileIn, append)){
                System.out.println("Исходный текст: " + text.toString());
                fileWriter.write(text.toString());
                fileWriter.flush();
                System.out.println("Файл " + pathOut + " был успешно копирован в " + pathIn + "!");
            }
        } catch (FileNotFoundException e) {
            throw new NotFoundFileException(ERR_MESSAGE + "Исходный файл не найден!");
        } catch (IOException e) {
            throw new ReadFileException(ERR_MESSAGE + "Ошибка прочтения исходного файла!");
        } catch (CreateFileException createFileException) {
            throw new CreateFileException(ERR_MESSAGE + " Не удалось создать копию файла!");
        }
    }
}
