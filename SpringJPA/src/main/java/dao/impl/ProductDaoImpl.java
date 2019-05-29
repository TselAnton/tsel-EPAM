package dao.impl;

import controller.ConnectionController;
import dao.ProductDao;
import generator.collection.ArraysGenerator;
import generator.entity.ProductGenerator;
import model.impl.Product;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Autowired
    private ProductGenerator productGenerator;

    @Autowired
    @Qualifier("productArrayList")
    private static ArraysGenerator arraysGenerator;

    @Autowired
    private EntityManager entityManager;

    private Connection connection;

    private final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException ");


    public ProductDaoImpl(ConnectionController connectionController) {
        connection = connectionController.getConnection();
    }

    @Override
    public boolean addProduct(Product product) {
        entityManager.getTransaction().commit();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        entityManager.close();

        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        entityManager.close();

        return true;
    }

    @Override
    public boolean updateProductDiscount(Product product) {
        return updateProduct(product);
    }

    @Override
    public Product getProductById(int id) {

        Query query = entityManager.createQuery("SELECT p FROM Product p WHERE p.id = :id", User.class);
        query.setParameter("id", id);

        return (Product) query.getSingleResult();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = entityManager.createNativeQuery("SELECT p FROM Product p", Product.class)
                .getResultList();

        return products;
    }

//    public static List<Product> getProductsByResultSet(ResultSet rs, Logger logger, Marker marker, ProductGenerator productGenerator) {
//        List<Product> productList = null;
//        try {
//            productList = ArrayList.class.newInstance();
//        } catch (InstantiationException | IllegalAccessException e) {
//            logger.error(marker, e.getMessage(), e);
//        }
//
//        try {
//            while (rs.next()) {
//                Product product = productGenerator.generateNewProduct();
//                product.setId(rs.getInt("id"));
//                product.setName(rs.getString("name"));
//                product.setCategoryId(rs.getInt("category_id"));
//                product.setBrand(rs.getString("brand"));
//                product.setPrice(rs.getInt("price"));
//                product.setQty(rs.getInt("qty"));
//                product.setDiscount(rs.getInt("discount"));
//                product.setDescription(rs.getString("description"));
//
//                productList.add(product);
//            }
//        } catch (SQLException e) {
//            logger.error(marker, "Can't get model Product form ResultSet", e);
//        }
//
//        return productList;
//    }
}
