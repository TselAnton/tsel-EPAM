package entity;

public class Item {

    private int id;
    private boolean isUsing;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isUsing() {
        return isUsing;
    }

    public void setUsing(boolean using) {
        isUsing = using;
    }

    public Item(int id) {
        this.id = id;
        this.isUsing = false;
    }

    @Override
    public String toString() {
        return "Item #" + id;
    }
}
