package service.impl.extend;

import dao.pool.impl.CategoryDaoPoolImpl;
import model.impl.Category;
import org.springframework.beans.factory.annotation.Autowired;
import service.impl.AbstractListOfNames;

import java.util.HashMap;

public final class CategoryList extends AbstractListOfNames<Integer, Category> {

    @Autowired
    private CategoryDaoPoolImpl categoryDaoPool;

    private HashMap<Integer, Category> categories;

    public CategoryList() {
    }


    @Override
    public void initialize() {
        this.categories = categoryDaoPool.getAllCategory();
        this.setHashMap(categories);
    }
}
