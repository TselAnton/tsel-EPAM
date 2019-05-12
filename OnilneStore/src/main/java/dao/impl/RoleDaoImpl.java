package dao.impl;

import controller.ConnectionController;
import dao.RoleDao;
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

public class RoleDaoImpl implements RoleDao {

    private Connection connection = ConnectionController.getInstance().getConnection();

    private final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");

    @Override
    public HashMap<Integer, Role> getAllRoles() {
        HashMap<Integer, Role> roles = new HashMap<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"role\"");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                role.setQty(rs.getInt("qty"));

                roles.put(rs.getInt("id"), role);
            }
        } catch (SQLException e) {
            logger.error(MARKER, "SQLException", e);
        }

        return roles;
    }
}
