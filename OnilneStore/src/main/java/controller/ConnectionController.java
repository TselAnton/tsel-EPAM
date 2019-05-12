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
public final class ConnectionController {

    private final String URL    = "jdbc:postgresql://localhost:5432/online_store";
    private final String LOGIN  = "postgres";
    private final String PASS   = "qwerty";
    private final String DRIVER = "org.postgresql.Driver";

    private final Marker MARKER = MarkerFactory.getMarker("Exception ");
    private final Logger logger = LoggerFactory.getLogger(ConnectionController.class.getName());

    private Connection connection = null;

    private static ConnectionController init = null;


    private ConnectionController() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASS);
        } catch (ClassNotFoundException e) {
            logger.error(MARKER, "ClassNotFound", e);
        } catch (SQLException e) {
            logger.error(MARKER, "SQLException", e);
        }
    }

    public static ConnectionController getInstance() {
        if (init == null) {
            init = new ConnectionController();
        }
        return init;
    }

    public Connection getConnection() {
        return connection;
    }
}
