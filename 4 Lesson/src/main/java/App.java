import enity.Note;
import utility.CrazyLogger;
import utility.InMemoryNoteBookProvider;
import utility.NoteBookView;

public class App {
    public static void main(String[] args) {
        InMemoryNoteBookProvider provider = InMemoryNoteBookProvider.GetInit();
        CrazyLogger logger = CrazyLogger.getInstanse();

        NoteBookView view = new NoteBookView() {
            // Anonymous class
            public void print(Note note) {
                System.out.println("Single Note");
                System.out.println(note.toString());
                System.out.println();
            }
        };

        logger.showAllLog();    // Пытаемся вывести пустой лог
        logger.findMessage("Some"); // Пытаемся найти что-нибудь в пустом логе
        System.out.println();

        view.print(provider.getRandomNote());   // Один Note
        logger.addMessage("Create one Note.");
        view.print(provider.getRandomNote(), provider.getRandomNote(), provider.getRandomNote()); // Несколько Note
        logger.addMessage("Create many Notes.");
        view.print(provider.getRandomNoteBook()); // Случайный NoteBook
        logger.addMessage("Create random NoteBook.");
        view.print(provider.getNoteBooks());    // Массив NoteBook
        logger.addMessage("Create array of NoteBook.");

        logger.showAllLog(); // Выводим весь лог

        System.out.println();
        logger.findMessage("Book"); // Ищем вхождения в логе

        System.out.println();
        logger.findMessage("Java"); // Ищем то, чего нет в логе
    }
}
