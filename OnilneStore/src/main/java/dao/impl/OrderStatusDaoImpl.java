package dao.impl;

import controller.ConnectionController;
import dao.OrderStatusDao;
import entity.OrderStatus;
import entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class OrderStatusDaoImpl implements OrderStatusDao {

    private Connection connection = ConnectionController.getInstance().getConnection();

    private final Logger logger = LoggerFactory.getLogger(OrderStatusDaoImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");

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
            logger.error(MARKER, "SQLException", e);
        }

        return statuses;
    }
}
