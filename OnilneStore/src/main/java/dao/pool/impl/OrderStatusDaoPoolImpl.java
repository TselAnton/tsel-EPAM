package dao.pool.impl;

import controller.ConnectionController;
import dao.pool.OrderStatusDaoPool;
import model.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class OrderStatusDaoPoolImpl implements OrderStatusDaoPool {

    private Connection connection = ConnectionController.getInstance().getConnection();

    private final Logger LOGGER = LoggerFactory.getLogger(OrderStatusDaoPoolImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException ");

    @Override
    public HashMap<Integer, OrderStatus> getAllStatuses() {
        HashMap<Integer, OrderStatus> statuses = new HashMap<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"order_status\"");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                OrderStatus status = new OrderStatus();
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
