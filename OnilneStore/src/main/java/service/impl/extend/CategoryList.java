package service.impl.extend;

import dao.pool.impl.CategoryDaoPoolImpl;
import model.Category;
import service.impl.AbstractListOfNames;

import java.util.HashMap;

public final class CategoryList extends AbstractListOfNames<Integer, Category> {

    private final CategoryDaoPoolImpl categoryDaoImpl = new CategoryDaoPoolImpl();
    private HashMap<Integer, Category> categories;

    public CategoryList() {
        this.categories = categoryDaoImpl.getAllCategory();
        this.setHashMap(categories);
    }
}
