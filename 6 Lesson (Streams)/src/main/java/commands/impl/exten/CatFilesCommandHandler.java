package commands.impl.exten;

import commands.impl.AbstractCommandHandler;
import exeptions.NotFoundFileException;
import exeptions.ReadFileException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CatFilesCommandHandler extends AbstractCommandHandler {
    private final String ERR_MESSAGE = "Ошибка чтения файла! ";

    public CatFilesCommandHandler() {
        super("-path", new String[]{"Вывод содержимого файла в консоль", "-path", "Путь к файлу"});
    }

    public void handle(String filePath) throws NotFoundFileException, ReadFileException {
        filePath = filePath.replaceAll("\"", "");
        StringBuilder text = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath)){
            int byteChar;
            while ((byteChar = fileReader.read()) != -1) {
                text.append((char) byteChar);
            }
            System.out.println("Файл " + filePath + ":\n" + text + "\n");
        } catch (FileNotFoundException e) {
            throw new NotFoundFileException(ERR_MESSAGE + "Файл не найден!");
        } catch (IOException e) {
            throw new ReadFileException(ERR_MESSAGE + "Файл не может быть прочитан!");
        }
    }
}
