package view.impl;

import view.ViewInterface;


public abstract class ViewAbstract implements ViewInterface {

    private int level;

    public ViewAbstract(int level) {
        this.level = level;
    }

    public void showMessage(String message) {
        System.out.println(message + "\n");
    }

    public int getLevel() {
        return level;
    }
}
