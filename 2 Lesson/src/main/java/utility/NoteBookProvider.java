package utility;

import enity.Note;
import enity.NoteBook;

import java.util.Random;

/**
 * NoteBookProvider
 */
public class NoteBookProvider {
    private static NoteBookProvider singleton;  // Ленивая реализация Singleton
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
    private NoteBookProvider() {}

    /**
     * Custom constructor
     * @return  Single class instance
     */
    public static NoteBookProvider init() {
        if (singleton == null) {
            return new NoteBookProvider();
        }
        return null;
    }

    /**
     * Get Random NoteBook
     * @return  NoteBook
     */
    public NoteBook getRandomNoteBook() {
        return noteBooks[new Random().nextInt(noteBooks.length)];
    }

    /**
     * Get Random Note
     * @return Note
     */
    public Note getRandomNote() {
        Note[] notes =  noteBooks[new Random().nextInt(noteBooks.length)].getNotes();
        return notes[new Random().nextInt(notes.length)];
    }

    /**
     * Get all mass NoteBook
     * @return NoteBook[]
     */
    public NoteBook[] getNoteBooks() {
        return noteBooks;
    }
}
