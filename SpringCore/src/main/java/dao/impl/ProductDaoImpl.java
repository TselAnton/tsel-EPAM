package dao.impl;

import controller.ConnectionController;
import dao.ProductDao;
import generator.collection.ArraysGenerator;
import generator.entity.ProductGenerator;
import model.impl.Product;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Autowired
    private ProductGenerator productGenerator;

    @Autowired
    @Qualifier("productArrayList")
    private static ArraysGenerator arraysGenerator;


    private Connection connection;

    private final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException ");


    public ProductDaoImpl(ConnectionController connectionController) {
        connection = connectionController.getConnection();
    }

    @Override
    public boolean addProduct(Product product) {
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

            int countOfExecute = statement.executeUpdate();
            if (countOfExecute > 0) return true;

        } catch (SQLException e) {
            LOGGER.error(MARKER, "Can't execute statement addProduct", e);
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
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

            int countOfExecute = statement.executeUpdate();
            if (countOfExecute > 0) return true;
        } catch (SQLException e) {
            LOGGER.error(MARKER, "Can't execute statement updateProduct", e);
        }
        return false;
    }

    @Override
    public boolean updateProductDiscount(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE \"product\" SET discount = ? WHERE \"product\".name = ?");

            statement.setInt(1, product.getDiscount());
            statement.setString(2, product.getName());

            int countOfExecute = statement.executeUpdate();
            if (countOfExecute > 0) return true;
        } catch (SQLException e) {
            LOGGER.error(MARKER, "Can't execute statement updateProductDiscount", e);
        }
        return false;
    }

    @Override
    public Product getProductById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE product.id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            List<Product> productList = getProductsByResultSet(rs, LOGGER, MARKER, productGenerator);
            if (productList.size() > 0) return productList.get(0);
        } catch (SQLException e) {
            LOGGER.error(MARKER, "Can't execute statement getProductById", e);
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = Arrays.asList();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product");
            ResultSet rs = statement.executeQuery();
            products = getProductsByResultSet(rs, LOGGER, MARKER, productGenerator);

        } catch (SQLException e) {
            LOGGER.error(MARKER, "Can't execute statement getAllProducts", e);
        }
        return products;
    }

    public static List<Product> getProductsByResultSet(ResultSet rs, Logger logger, Marker marker, ProductGenerator productGenerator) {
        List<Product> productList = null;
        try {
            productList = ArrayList.class.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error(marker, e.getMessage(), e);
        }

        try {
            while (rs.next()) {
                Product product = productGenerator.generateNewProduct();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getInt("price"));
                product.setQty(rs.getInt("qty"));
                product.setDiscount(rs.getInt("discount"));
                product.setDescription(rs.getString("description"));

                productList.add(product);
            }
        } catch (SQLException e) {
            logger.error(marker, "Can't get model Product form ResultSet", e);
        }

        return productList;
    }
}
