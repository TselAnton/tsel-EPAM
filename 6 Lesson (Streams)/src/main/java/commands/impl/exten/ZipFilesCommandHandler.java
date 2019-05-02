package commands.impl.exten;

import commands.impl.AbstractCommandHandler;
import exeptions.CommandArgumentsIncorrectExeption;
import exeptions.CreateFileException;
import exeptions.NotFoundFileException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFilesCommandHandler extends AbstractCommandHandler {
    private final String ERR_MESSAGE = "Ошибка создания архива! ";

    public ZipFilesCommandHandler() {
        super("\"path_to_zip_file\" \"files...\"",
                new String[]{"Создание zip архива", "-path_to_zip_file", "Путь создания архива в двойных ковычках",
                        "-files...", "Пути к файлам для последующей запаковки, в двойных ковычках"});
    }

    public void handle(String args) throws CommandArgumentsIncorrectExeption, CreateFileException, NotFoundFileException {
        String[] options = args.split("\"");
        try {
            String filePath = options[1];
            List<String> files = new ArrayList<>();
            for (int i = 3; i < options.length; i = i + 2) {
                files.add(options[i]);
            }
            zipFiles(filePath, files);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandArgumentsIncorrectExeption(ERR_MESSAGE + "Не верно указаны аргументы!");
        }
    }

    private void zipFiles(String filePath, List<String> files) throws NotFoundFileException, CreateFileException {
        ZipOutputStream zipOutputStream;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(filePath));
            for (String file : files) {
                zipOutputStream.putNextEntry(new ZipEntry(file));

                StringBuilder text = new StringBuilder();
                FileReader fileReader = new FileReader(file);
                int byteChar;
                while ((byteChar = fileReader.read()) != -1) {
                    text.append((char) byteChar);
                }
                zipOutputStream.write(text.toString().getBytes());
                zipOutputStream.closeEntry();
            }
            zipOutputStream.close();
            System.out.println("Архив " + filePath + " успешно создан!");
        } catch (FileNotFoundException e) {
            throw new NotFoundFileException(ERR_MESSAGE + "Не удалось обнаружить все указанные файлы!");
        } catch (IOException e) {
            throw new CreateFileException(ERR_MESSAGE + "Не удалось создать архив!");
        }
    }
}
