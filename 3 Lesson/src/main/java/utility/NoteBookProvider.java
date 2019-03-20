package utility;

import enity.Note;
import enity.NoteBook;

/**
 * Interface
 */
public interface NoteBookProvider {

    NoteBook getRandomNoteBook();
    Note getRandomNote();
    NoteBook[] getNoteBooks();
}
