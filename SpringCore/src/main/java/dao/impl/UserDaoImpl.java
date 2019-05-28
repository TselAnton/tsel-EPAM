package dao.impl;

import controller.ConnectionController;
import dao.UserDao;
import generator.entity.UserGenerator;
import model.impl.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException ");

    @Autowired
    private UserGenerator userGenerator;

    private Connection connection;

    public UserDaoImpl(ConnectionController connectionController) {
        connection = connectionController.getConnection();
    }

    @Override
    public boolean addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO public.\"user\" (fio, login, password, phone, email, city, street, house, apartment, role_id) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setString(1, user.getFio());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getCity());
            statement.setString(7, user.getStreet());
            statement.setString(8, user.getHouse());
            statement.setString(9, user.getApartment());
            statement.setInt(10, user.getRoleId());

            int countOfExecute = statement.executeUpdate();
            if (countOfExecute > 0) return true;

        } catch (SQLException e) {
            LOGGER.error(MARKER, "Can't execute statement addUser", e);
        }
        return false;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM \"user\" WHERE \"user\".login = ?");

            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                user = userGenerator.generateNewUser();
                user.setId(rs.getInt("id"));
                user.setFio(rs.getString("fio"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setCity(rs.getString("city"));
                user.setStreet(rs.getString("street"));
                user.setHouse(rs.getString("house"));
                user.setApartment(rs.getString("apartment"));
                user.setRegistrationDate(rs.getDate("registration_date"));
                user.setRoleId(rs.getInt("role_id"));
                user.setQtyOrders(rs.getInt("qty_orders"));
            }

        } catch (SQLException e) {
            LOGGER.error(MARKER, "Can't execute statement getUserByLogin", e);
        }

        return user;
    }
}
