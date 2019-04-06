package commands;

import commands.inter.Command;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFiles implements Command {
    private final String arg = "\"path_to_zip_file\" \"files...\"";
    private final String description = "Создание zip архива\n         " +
            "-path_to_zip_file — Путь создания архива в двойных ковычках\n         " +
            "-files... — Пути к файлам для последующей запаковки, в двойных ковычках";
    private final String ERR_MESSAGE = "Ошибка создания архива! ";

    public void doCommand(String args) {
        String[] options = args.split("\"");
        try {
            String filePath = options[1];
            List<String> files = new ArrayList<>();
            for (int i = 3; i < options.length; i = i + 2) {
                files.add(options[i]);
            }
            zipFiles(filePath, files);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ERR_MESSAGE + "Не верно указаны аргументы!");
        }
    }

    private void zipFiles(String filePath, List<String> files) {
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
            System.out.println(ERR_MESSAGE + "Не удалось обнаружить все указанные файлы!");
        } catch (IOException e) {
            System.out.println(ERR_MESSAGE + "Не удалось создать архив!");
        }
    }

    public String getArgs() {
        return arg + "      " + description;
    }
}
