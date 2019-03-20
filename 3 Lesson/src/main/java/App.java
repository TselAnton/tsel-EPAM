import enity.Note;
import utility.InMemoryNoteBookProvider;
import utility.NoteBookView;

public class App {
    public static void main(String[] args) {
        InMemoryNoteBookProvider provider = InMemoryNoteBookProvider.GetInit();
        NoteBookView view = new NoteBookView() {
            // Anonymous class
            public void print(Note note) {
                System.out.println("Single Note");
                System.out.println(note.toString());
                System.out.println();
            }
        };

        view.print(provider.getRandomNote());   // Один Note
        view.print(provider.getRandomNote(), provider.getRandomNote(), provider.getRandomNote()); // Несколько Note
        view.print(provider.getRandomNoteBook()); // Случайный NoteBook
        view.print(provider.getNoteBooks());    // Массив NoteBook
    }
}
