package dao.pool.impl;

import controller.ConnectionController;
import dao.pool.CategoryDaoPool;
import model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CategoryDaoPoolImpl implements CategoryDaoPool {

    private Connection connection = ConnectionController.getInstance().getConnection();

    private final Logger LOGGER = LoggerFactory.getLogger(CategoryDaoPoolImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException ");

    @Override
    public HashMap<Integer, Category> getAllCategory() {
        HashMap<Integer, Category> categories = new HashMap<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"product_category\"");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Category category = new Category();
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
