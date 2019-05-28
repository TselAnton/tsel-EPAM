package service.impl;

import service.ListOfNames;

import java.util.HashMap;

public abstract class AbstractListOfNames<K, V> implements ListOfNames<K, V> {
    private HashMap<K, V> hashMap = null;

    public void setHashMap(HashMap<K, V> hashMap) {
        this.hashMap = hashMap;
    }

    public HashMap<K, V> getAllNames() {
        return hashMap;
    }

    public abstract void initialize();
}
