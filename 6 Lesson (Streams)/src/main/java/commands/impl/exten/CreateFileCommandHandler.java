package commands.impl.exten;

import commands.impl.AbstractCommandHandler;
import exeptions.CreateFileException;
import exeptions.PathCorrectExeption;

import java.io.File;
import java.io.IOException;

public class CreateFileCommandHandler extends AbstractCommandHandler {
    private final String ERR_MESSAGE = "Ошибка создания файла! ";

    public CreateFileCommandHandler() {
        super("-path", new String[]{"Создание файла", "-path", "Путь к файлу"});
    }

    public void handle(String filePath) throws CreateFileException, PathCorrectExeption {
        filePath = filePath.replaceAll("\"","");
        File file = new File(filePath);
        try {
            boolean newFile = file.createNewFile();
            if (newFile) System.out.println("Файл " + filePath + " успешно создан!");
            else throw new CreateFileException(ERR_MESSAGE + "Файл " + filePath + " уже существует!");
        } catch (IOException e) {
            throw new PathCorrectExeption(ERR_MESSAGE + "Неверно указан путь!");
        }
    }
}
