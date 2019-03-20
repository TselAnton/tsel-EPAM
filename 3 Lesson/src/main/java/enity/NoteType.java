package enity;

public enum  NoteType {
    NOTE("Заметка"), TASK("Задача");

    private String noteType;

    /**
     * Constructor
     * @param noteType Note type
     */
    NoteType(String noteType) {
        this.noteType = noteType;
    }

    public String getRusName() {
        return this.noteType;
    }
}
