package dao;

import entity.Category;

import java.util.HashMap;

public interface CategoryDao {

    public HashMap<Integer, Category> getAllCategory();
}
