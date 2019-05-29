package dao.pool.impl;

import controller.ConnectionController;
import dao.impl.ProductDaoImpl;
import dao.pool.ShopCartDaoPool;
import generator.collection.ArraysGenerator;
import generator.entity.ProductGenerator;
import model.impl.Order;
import model.impl.Product;
import model.impl.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShopCartDaoPoolImpl implements ShopCartDaoPool {

    @Autowired
    @Qualifier("productArrayList")
    private ArraysGenerator arraysGenerator;

    @Autowired
    private ProductGenerator productGenerator;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    private Connection connection;

    private final Logger LOGGER = LoggerFactory.getLogger(ShopCartDaoPoolImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException ");

    public ShopCartDaoPoolImpl(ConnectionController connectionController) {
        connection = connectionController.getConnection();
    }

    @Override
    public boolean addToCaryProduct(Order order, Product product) {
        boolean result = false;
        try {
            // Очередной костыль
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO basket (order_id, product_id) VALUES (?, ?)");
            statement.setInt(1, order.getId());
            statement.setInt(2, product.getId());

            int count = statement.executeUpdate();
            if (count == 1) result = true;
        } catch (SQLException e) {
            LOGGER.error(MARKER, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Product> getAllProductsByOrderId(int orderId) {

        Query query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> productList = query.getResultList();

        return productList;
    }
}
