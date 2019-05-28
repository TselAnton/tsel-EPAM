package dao.pool.impl;

import controller.ConnectionController;
import dao.pool.OrderStatusDaoPool;
import generator.collection.HashMapGenerator;
import generator.entity.OrderStatusGenerator;
import model.impl.OrderStatus;
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

public class OrderStatusDaoPoolImpl implements OrderStatusDaoPool {

    @Autowired
    private OrderStatusGenerator orderStatusGenerator;

    @Autowired
    @Qualifier("orderStatusHashMap")
    private HashMapGenerator hashMapGenerator;

    private Connection connection;

    private final Logger LOGGER = LoggerFactory.getLogger(OrderStatusDaoPoolImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException ");


    public OrderStatusDaoPoolImpl(ConnectionController connectionController) {
        connection = connectionController.getConnection();
    }

    @Override
    public HashMap<Integer, OrderStatus> getAllStatuses() {
        HashMap<Integer, OrderStatus> statuses = hashMapGenerator.generateHashMap();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"order_status\"");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                OrderStatus status = orderStatusGenerator.generateNewOrderStatus();
                status.setId(rs.getInt("id"));
                status.setName(rs.getString("name"));

                statuses.put(rs.getInt("id"), status);
            }
        } catch (SQLException e) {
            LOGGER.error(MARKER, e.getMessage(), e);
        }

        return statuses;
    }
}
