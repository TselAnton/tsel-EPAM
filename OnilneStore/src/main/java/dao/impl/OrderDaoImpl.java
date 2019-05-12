package dao.impl;

import controller.ConnectionController;
import dao.OrderDao;
import entity.Order;
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

    private final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");

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
            logger.error(MARKER, "SQLException", e);
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
            logger.error(MARKER, "SQLException", e);
        }

        return result;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Order getLastOrderByUserId(int userId) {
        Order order = null;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT \"order\".* FROM \"order\" WHERE \"order\".date = " +
                            "(SELECT MAX(\"order\".date) FROM \"order\") AND \"order\".user_id = ?;");

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                order = new Order();

                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setDate(rs.getDate("date"));
                order.setStatusId(rs.getInt("status_id"));
            }
        } catch (SQLException e) {
            logger.error(MARKER, "SQLException", e);
        }

        return order;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public List<Order> getAllOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM \"order\" WHERE \"order\".user_id = ?");

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setDate(rs.getDate("date"));
                order.setStatusId(rs.getInt("status_id"));

                orders.add(order);
            }
        } catch (SQLException e) {
            logger.error(MARKER, "SQLException", e);
        }

        return orders;
    }
}
