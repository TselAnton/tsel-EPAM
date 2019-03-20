package utility;

import enity.Note;
import enity.NoteBook;

import java.util.Random;

/**
 * InMemoryNoteBookProvider
 */
public class InMemoryNoteBookProvider implements NoteBookProvider {
    private static InMemoryNoteBookProvider singleton;  // Ленивая реализация Singleton
    private NoteBook[] noteBooks;

    {
        noteBooks = new NoteBook[new Random().nextInt(10) + 1];
        for (int i = 0; i < noteBooks.length; i++) {
            noteBooks[i] = new NoteBook();
        }
    }

    /**
     * Private Constructor
     * (For singelton)
     */
    private InMemoryNoteBookProvider() {}

    /**
     * Custom constructor
     * @return  Single class instance
     */
    public static InMemoryNoteBookProvider GetInit() {
        if (singleton == null) {
            return new InMemoryNoteBookProvider();
        }
        return null;
    }

    /**
     * Get Random NoteBook
     * @return  NoteBook
     */
    @Override
    public NoteBook getRandomNoteBook() {
        return noteBooks[new Random().nextInt(noteBooks.length)];
    }

    /**
     * Get Random Note
     * @return Note
     */
    @Override
    public Note getRandomNote() {
        Note[] notes =  noteBooks[new Random().nextInt(noteBooks.length)].getNotes();
        return notes[new Random().nextInt(notes.length)];
    }

    /**
     * Get all mass NoteBook
     * @return NoteBook[]
     */
    @Override
    public NoteBook[] getNoteBooks() {
        return noteBooks;
    }
}
