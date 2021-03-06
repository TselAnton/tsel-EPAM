package dao.pool.impl;

import controller.ConnectionController;
import dao.pool.CategoryDaoPool;
import generator.collection.HashMapGenerator;
import generator.entity.CategoryGenerator;
import model.impl.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CategoryDaoPoolImpl implements CategoryDaoPool {

    @Autowired
    private CategoryGenerator categoryGenerator;

    @Autowired
    @Qualifier("categoryHashMap")
    private HashMapGenerator hashMapGenerator;

    private Connection connection;

    private final Logger LOGGER = LoggerFactory.getLogger(CategoryDaoPoolImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException ");

    public CategoryDaoPoolImpl(ConnectionController connectionController) {
        connection = connectionController.getConnection();
    }

    @Override
    public HashMap<Integer, Category> getAllCategory() {
        HashMap<Integer, Category> categories = hashMapGenerator.generateHashMap();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"product_category\"");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Category category = categoryGenerator.generateNewCategory();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));

                categories.put(rs.getInt("id"), category);
            }
        } catch (SQLException e) {
            LOGGER.error(MARKER, e.getMessage(), e);
        }

        return categories;
    }
}
