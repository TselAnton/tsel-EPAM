package commands.impl.exten;

import commands.impl.AbstractCommandHandler;
import exeptions.DeleteFileExeption;
import exeptions.NotFoundFileException;

import java.io.File;

public class DeleteFileCommandHandler extends AbstractCommandHandler {
    private final String ERR_MESSAGE = "Ошибка удаления файла! ";

    public DeleteFileCommandHandler() {
        super("-path", new String[]{"Удаление файла", "-path", "Путь к файлу"});
    }

    public void handle(String filePath) throws NotFoundFileException, DeleteFileExeption {
        filePath = filePath.replaceAll("\"","");
        File file = new File(filePath);
        if (!file.exists()) throw new NotFoundFileException(ERR_MESSAGE + "Файл " + filePath + " не найден!");
        else {
            boolean isDone = file.delete();
            if (isDone) System.out.println("Файл " + filePath + " успешно удалён!");
            else throw new DeleteFileExeption(ERR_MESSAGE + "Файл " + filePath + " не удалось удалить!");
        }
    }
}
