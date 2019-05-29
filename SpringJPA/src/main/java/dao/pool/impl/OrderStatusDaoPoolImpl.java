package dao.pool.impl;

import controller.ConnectionController;
import dao.pool.OrderStatusDaoPool;
import generator.collection.HashMapGenerator;
import generator.entity.OrderStatusGenerator;
import model.impl.Category;
import model.impl.OrderStatus;
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

public class OrderStatusDaoPoolImpl implements OrderStatusDaoPool {

    @Autowired
    private OrderStatusGenerator orderStatusGenerator;

    @Autowired
    @Qualifier("orderStatusHashMap")
    private HashMapGenerator hashMapGenerator;

    @Autowired
    private EntityManager entityManager;

    private Connection connection;

    private final Logger LOGGER = LoggerFactory.getLogger(OrderStatusDaoPoolImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException ");


    public OrderStatusDaoPoolImpl(ConnectionController connectionController) {
        connection = connectionController.getConnection();
    }

    @Override
    public HashMap<Integer, OrderStatus> getAllStatuses() {
        HashMap<Integer, OrderStatus> orderStatusHashMap = hashMapGenerator.generateHashMap();

        Query query = entityManager.createQuery("SELECT o FROM OrderStatus o", OrderStatus.class);
        List<OrderStatus> orderStatusList = query.getResultList();

        for (OrderStatus o : orderStatusList) {
            orderStatusHashMap.put(o.getId(), o);
        }

        return orderStatusHashMap;
    }
}
