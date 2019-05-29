package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Контроллер соединения
 */
@Deprecated
public final class ConnectionController {

    private final Marker MARKER = MarkerFactory.getMarker("Exception ");
    private final Logger LOGGER = LoggerFactory.getLogger(ConnectionController.class.getName());

    private Connection connection = null;

    public ConnectionController(String url, String login, String pass, String driver) {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, login, pass);
        } catch (ClassNotFoundException e) {
            LOGGER.error(MARKER, "Driver is not found", e);
        } catch (SQLException e) {
            LOGGER.error(MARKER, "Can't connect to DataBase", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
