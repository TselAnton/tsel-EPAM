package dao.pool;

import model.Category;

import java.util.HashMap;

public interface CategoryDaoPool {

    public HashMap<Integer, Category> getAllCategory();
}
