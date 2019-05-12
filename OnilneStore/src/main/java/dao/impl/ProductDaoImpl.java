package dao.impl;

import controller.ConnectionController;
import dao.ProductDao;
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

public class ProductDaoImpl implements ProductDao {

    private Connection connection = ConnectionController.getInstance().getConnection();

    private final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");

    @Override
    @SuppressWarnings("Duplicates")
    public int addProduct(Product product) {
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO product (\"name\", category_id, brand, price, qty, discount, description) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");

            statement.setString(1, product.getName());
            statement.setInt(2, product.getCategoryId());
            statement.setString(3, product.getBrand());
            statement.setInt(4, product.getPrice());
            statement.setInt(5, product.getQty());
            statement.setInt(6, product.getDiscount());
            statement.setString(7, product.getDescription());

            result = statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(MARKER, "SQLException", e);
        }
        return result;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public int updateProduct(Product product) {
        int result = 0;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE product SET category_id = ?, brand = ?, price = ?, qty = ?, discount = ?, " +
                            "description = ? WHERE product.name = ?");

            statement.setInt(1, product.getCategoryId());
            statement.setString(2, product.getBrand());
            statement.setInt(3, product.getPrice());
            statement.setInt(4, product.getQty());
            statement.setInt(5, product.getDiscount());
            statement.setString(6, product.getDescription());
            statement.setString(7, product.getName());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(MARKER, "SQLException", e);
        }

        return result;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public int updateProductDiscount(Product product) {
        int result = 0;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE \"product\" SET discount = ? WHERE \"product\".name = ?");

            statement.setInt(1, product.getDiscount());
            statement.setString(2, product.getName());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(MARKER, "SQLException", e);
        }

        return result;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Product getProductById(int id) {
        Product product = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE product.id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getInt("price"));
                product.setQty(rs.getInt("qty"));
                product.setDiscount(rs.getInt("discount"));
                product.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            logger.error(MARKER, "SQLException", e);
        }

        return product;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product");
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
