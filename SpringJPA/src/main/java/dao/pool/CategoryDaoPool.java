package dao.pool;

import model.impl.Category;

import java.util.HashMap;

public interface CategoryDaoPool {

    public HashMap<Integer, Category> getAllCategory();
}
