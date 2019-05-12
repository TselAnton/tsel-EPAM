package dao.impl;

import controller.ConnectionController;
import dao.ShopCartDao;
import entity.Order;
import entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopCartDaoImpl implements ShopCartDao {

    private Connection connection = ConnectionController.getInstance().getConnection();

    private final Logger logger = LoggerFactory.getLogger(ShopCartDaoImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");

    @Override
    public boolean addToCaryProduct(Order order, Product product) {
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO basket (order_id, product_id) VALUES (?, ?)");
            statement.setInt(1, order.getId());
            statement.setInt(2, product.getId());

            int count = statement.executeUpdate();
            if (count == 1) result = true;
        } catch (SQLException e) {
            logger.error(MARKER, "SQLException", e);
        }
        return result;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public List<Product> getAllProductsByOrderId(int orderId) {

        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM product INNER JOIN basket ON " +
                            "basket.product_id = product.id AND basket.order_id = ?");

            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getInt("price"));
                product.setQty(rs.getInt("qty"));
                product.setDiscount(rs.getInt("discount"));
                product.setDescription(rs.getString("description"));

                products.add(product);
            }

        } catch (SQLException e) {
            logger.error(MARKER, "SQLException", e);
        }

        return products;
    }
}
