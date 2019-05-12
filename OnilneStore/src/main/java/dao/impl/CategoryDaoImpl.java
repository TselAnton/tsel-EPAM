package dao.impl;

import controller.ConnectionController;
import dao.CategoryDao;
import entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CategoryDaoImpl implements CategoryDao {

    private Connection connection = ConnectionController.getInstance().getConnection();

    private final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");

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
            logger.error(MARKER, "SQLException", e);
        }

        return categories;
    }
}
