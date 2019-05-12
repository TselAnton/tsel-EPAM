package service.impl.extend;

import dao.impl.CategoryDaoImpl;
import entity.Category;
import service.impl.AbstractListOfNames;

import java.util.HashMap;

public final class CategoryList extends AbstractListOfNames<Integer, Category> {

    private final CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();
    private HashMap<Integer, Category> category;

    public CategoryList() {
        this.category = categoryDaoImpl.getAllCategory();
        this.setHashMap(category);
    }
}
