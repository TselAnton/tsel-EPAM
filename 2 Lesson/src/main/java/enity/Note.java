package enity;

import java.util.Date;

/**
 * Note
 */
public class Note {
    private String text;
    private Date date;

    /**
     * Constructor
     * @param text  text
     * @param date  date
     */
    public Note(String text, Date date) {
        this.text = text;
        this.date = date;
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
        return "Note {text = '" + this.text + '\'' + ", date = " + this.date + '}';
    }

    /**
     * equals
     * @param o Object
     * @return  this.equals(o)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note obj = (Note)o;
        if (obj.getDate().equals(this.date) && obj.getText().equals(this.text)) return true;
        return false;
    }

    /**
     * hashCode
     * @return  this.hashCode()
     */
    @Override
    public int hashCode() {
        int res = this.text != null ? this.text.hashCode() : 0;
        res = 31 * res + (this.date != null ? this.date.hashCode() : 0);
        return res;
    }
}
