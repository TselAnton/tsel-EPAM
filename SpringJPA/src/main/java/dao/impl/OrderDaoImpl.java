package dao.impl;

import dao.OrderDao;
import generator.collection.ArraysGenerator;
import generator.entity.OrderGenerator;
import model.impl.Order;
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
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Autowired
    @Qualifier("orderArrayList")
    private ArraysGenerator arraysGenerator;

    @Autowired
    private OrderGenerator orderGenerator;

    @Autowired
    EntityManager entityManager;

    private Connection connection;

    private final Logger LOGGER = LoggerFactory.getLogger(OrderDaoImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException ");


    @Override
    public boolean addOrder(Order order) {

        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
        entityManager.detach(order);
        entityManager.close();

        return true;
    }

    @Override
    public boolean updateOrder(Order order) {
        entityManager.getTransaction().begin();
        entityManager.merge(order);
        entityManager.getTransaction().commit();
        entityManager.close();

        return true;
    }

    @Override
    public Order getLastOrderByUserId(int userId) {

        Query query = entityManager
                .createQuery("SELECT o FROM Order o WHERE o.userId = :id ORDER BY o.date",
                        Order.class);

        query.setParameter("id", userId);
        return  (Order) query.getSingleResult();
    }

    @Override
    public List<Order> getAllOrdersByUserId(int userId) {
        return entityManager.createNativeQuery("SELECT o FROM Order o", Order.class).getResultList();
    }
}
