package utility;

import enity.Note;
import enity.NoteBook;

/**
 * NoteBookConsoleView
 */
abstract public class NoteBookConsoleView {

    /**
     * Single Note
     * ABSTRACT METHOD
     * @param note  Note
     */
    abstract public void print(Note note);

    /**
     * Many Notes
     * @param notes Variable number of parameters of Note
     */
    public void print(Note ... notes) {
        System.out.println("Many Notes");
        for (Note n : notes) {
            System.out.println(n.toString());
        }
        System.out.println();
    }

    /**
     * Single NoteBook
     * @param noteBook  NoteBook
     */
    public void print(NoteBook noteBook) {
        System.out.println("Single NoteBook");
        System.out.println(noteBook.toString());
        System.out.println();
    }

    /**
     * Array of NoteBook
     * @param noteBooks Array of NoteBook
     */
    public void print(NoteBook[] noteBooks) {
        System.out.println(noteBooks.length + " NoteBooks");
        for (NoteBook noteBook : noteBooks) {
            System.out.println(noteBook.toString());
        }
        System.out.println();
    }

}
