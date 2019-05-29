package dao.pool.impl;

import controller.ConnectionController;
import dao.pool.RoleDaoPool;
import generator.collection.HashMapGenerator;
import generator.entity.RoleGenerator;
import model.impl.Category;
import model.impl.OrderStatus;
import model.impl.Role;
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

public class RoleDaoPoolImpl implements RoleDaoPool {

    @Autowired
    private RoleGenerator roleGenerator;

    @Autowired
    @Qualifier("roleHashMap")
    private HashMapGenerator hashMapGenerator;

    @Autowired
    private EntityManager entityManager;

    private Connection connection;

    private final Logger LOGGER = LoggerFactory.getLogger(RoleDaoPoolImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException ");

    public RoleDaoPoolImpl(ConnectionController connectionController) {
        connection = connectionController.getConnection();
    }

    @Override
    public HashMap<Integer, Role> getAllRoles() {
        HashMap<Integer, Role> roleHashMap = hashMapGenerator.generateHashMap();

        Query query = entityManager.createQuery("SELECT r FROM Role r", Role.class);
        List<Role> roleList = query.getResultList();

        for (Role r : roleList) {
            roleHashMap.put(r.getId(), r);
        }

        return roleHashMap;
    }
}
