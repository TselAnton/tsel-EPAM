package enity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Object;

/**
 * Note
 */
public class Note {
    private String text;
    private Date date;
    private NoteType noteType;
    /**
     * Constructor
     * @param text  text
     * @param date  date
     */
    public Note(String text, Date date, String type) {
        this.text = text;
        this.date = date;
        this.noteType = NoteType.valueOf(type); // Set type of note
    }

    /**
     * getText
     * @return  this.text
     */
    public String getText() {
        return text;
    }

    /**
     * setText
     * @param text  text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * getDate
     * @return  this.date
     */
    public Date getDate() {
        return date;
    }

    /**
     * setDate
     * @param date  date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * toString
     * @return  Note.toString()
     */
    @Override
    public String toString() {
        return "Note {type = '" + noteType.getRusName() + "', text = '" + this.text + '\'' + ", date = " +
                new SimpleDateFormat("dd.MM.yyyy").format(this.date) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (text != null ? !text.equals(note.text) : note.text != null) return false;
        if (!date.equals(note.date)) return false;
        return noteType == note.noteType;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + date.hashCode();
        result = 31 * result + noteType.hashCode();
        return result;
    }
}
