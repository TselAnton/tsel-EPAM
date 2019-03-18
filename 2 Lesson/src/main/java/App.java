
import utility.NoteBookConsoleView;
import utility.NoteBookProvider;

public class App {
    public static void main(String[] args) {
        NoteBookProvider provider = NoteBookProvider.init();
        NoteBookConsoleView view = new NoteBookConsoleView();

        view.print(provider.getRandomNote());   // Один Note
        view.print(provider.getRandomNote(), provider.getRandomNote(), provider.getRandomNote()); // Несколько Note
        view.print(provider.getRandomNoteBook()); // Случайный NoteBook
        view.print(provider.getNoteBooks());    // Массив NoteBook
    }
}
