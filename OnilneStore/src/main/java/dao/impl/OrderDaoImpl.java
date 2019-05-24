package dao.impl;

import controller.ConnectionController;
import dao.OrderDao;
import model.Order;
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

public class OrderDaoImpl implements OrderDao {

    private Connection connection = ConnectionController.getInstance().getConnection();

    private final Logger LOGGER = LoggerFactory.getLogger(OrderDaoImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException ");

    @Override
    public boolean addOrder(Order order) {
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO \"order\" (user_id, date, status_id) VALUES (?, ?, ?)");

            statement.setInt(1, order.getUserId());
            statement.setDate(2, order.getDate());
            statement.setInt(3, order.getStatusId());

            result = statement.execute();
        } catch (SQLException e) {
            LOGGER.error(MARKER, "Execute return fall", e);
        }
        return result;
    }

    @Override
    public boolean updateOrder(Order order) {
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE \"order\" SET \"order\".status_id = ?, \"order\".date = ? WHERE \"order\".user_id = ?");

            statement.setInt(1, order.getStatusId());
            statement.setDate(2, order.getDate());
            statement.setInt(3, order.getUserId());

            result = statement.execute();
        } catch (SQLException e) {
            LOGGER.error(MARKER, "Can't execute statement UpdateOrder", e);
        }

        return result;
    }

    @Override
    public Order getLastOrderByUserId(int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM \"order\" WHERE \"order\".user_id = ? " +
                            "ORDER BY \"order\".date DESC LIMIT 1;");

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            List<Order> orderList = getOrdersByResultSet(rs);
            if (orderList.size() > 0) return orderList.get(0);
        } catch (SQLException e) {
            LOGGER.error(MARKER, "Can't execute statement getLastOrderByUserId", e);
        }
        return null;
    }

    @Override
    public List<Order> getAllOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM \"order\" WHERE \"order\".user_id = ?");

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            orders = getOrdersByResultSet(rs);
        } catch (SQLException e) {
            LOGGER.error(MARKER, "Can't execute statement getAllOrdersByUserId", e);
        }

        return orders;
    }

    private List<Order> getOrdersByResultSet(ResultSet rs) {
        List<Order> orderList = new ArrayList<>();
        try {
            while (rs.next()) {
                Order order = new Order();

                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setDate(rs.getDate("date"));
                order.setStatusId(rs.getInt("status_id"));

                orderList.add(order);
            }
        } catch (SQLException e) {
            LOGGER.error(MARKER, "Can't get model Order form ResultSet", e);
        }

        return orderList;
    }
}
