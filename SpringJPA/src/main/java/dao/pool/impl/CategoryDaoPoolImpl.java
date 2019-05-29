package dao.pool.impl;

import controller.ConnectionController;
import dao.pool.CategoryDaoPool;
import generator.collection.HashMapGenerator;
import generator.entity.CategoryGenerator;
import model.impl.Category;
import model.impl.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class CategoryDaoPoolImpl implements CategoryDaoPool {

    @Autowired
    private CategoryGenerator categoryGenerator;

    @Autowired
    @Qualifier("categoryHashMap")
    private HashMapGenerator hashMapGenerator;

    private Connection connection;

    @Autowired
    public EntityManager entityManager;

    private final Logger LOGGER = LoggerFactory.getLogger(CategoryDaoPoolImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException ");

    public CategoryDaoPoolImpl(ConnectionController connectionController) {
        connection = connectionController.getConnection();
    }

    @Override
    public HashMap<Integer, Category> getAllCategory() {
        HashMap<Integer, Category> categories = hashMapGenerator.generateHashMap();

        Query query = entityManager.createQuery("SELECT c FROM Category c", Category.class);
        List<Category> categoryList = query.getResultList();

        for (Category c : categoryList) {
            categories.put(c.getId(), c);
        }

        return categories;
    }
}
