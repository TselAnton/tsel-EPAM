package enity;


import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * NoteBook
 */
public class NoteBook {
    private Note[] notes;

    /**
     * Constructor
     */
    public NoteBook() {
        String[] vocabulary = {"Hello!", "Bye!", "Hail!", "This is my post!", "It's just an inscription.", "Simple text."};
        this.notes = new Note[new Random().nextInt(10) + 1];    // Генерируем случайное кол-во записей
        for (int i = 0; i < notes.length; i++) {
            this.notes[i] = new Note((vocabulary[new Random().nextInt(3)] + " " + vocabulary[new Random().nextInt(3) + 3]),
                    new Date(System.currentTimeMillis() - new Random().nextLong() % 432000000), // Задаём в пределах -5 до 0 дней от сегодня
                    new Random().nextInt(2) == 1 ? "NOTE" : "TASK");
        }
    }

    /**
     * getLength
     * @return this.notes.length
     */
    public int getLength() {
        return this.notes.length;
    }

    /**
     * getNotes
     * @return this.notes
     */
    public Note[] getNotes() {
        return notes;
    }

    /**
     * toString
     * @return this.toString()
     */
    @Override
    public String toString() {
        String result =  "NoteBook {length = " + notes.length + ", ";
        for (int i = 0; i < getLength(); i++) {
            result += notes[i].toString();
            if (i + 1 != getLength()) {
                result += ", \n";
            }
        }
        return result + "}\n";
    }

    /**
     * equals
     * @param o Object
     * @return  this.equals(o)
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != getClass()) return false;
        NoteBook tempBook = (NoteBook)o;
        if (getLength() != tempBook.getLength()) return false;

        return Arrays.equals(notes, tempBook.getNotes());
    }

    /**
     * hashCode
     * @return this.hashCode()
     */
    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < getLength(); i++) {
            result = 31 * result + notes[i].hashCode();
        }
        return result;
    }
}
