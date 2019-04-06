package commands;

import commands.inter.Command;

import java.io.File;

public class DeleteFile implements Command {

    private final String arg = "-path";
    private final String description = "Удаление файла\n        -path — Путь к файлу";
    private final String ERR_MESSAGE = "Ошибка удаления файла! ";

    public void doCommand(String filePath) {
        filePath = filePath.replaceAll("\"","");
        File file = new File(filePath);
        if (!file.exists()) System.out.println(ERR_MESSAGE + "Файл " + filePath + " не найден!");
        else {
            boolean isDone = file.delete();
            if (isDone) System.out.println("Файл " + filePath + " успешно удалён!");
            else System.out.println(ERR_MESSAGE + "Файл " + filePath + " не удалось удалить!");
        }
    }

    public String getArgs() {
        return arg + "      " + description;
    }
}
