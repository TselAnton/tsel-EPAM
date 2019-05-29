package dao.impl;

import dao.UserDao;
import generator.entity.UserGenerator;
import model.impl.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {

    private final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException ");

    @Autowired
    private UserGenerator userGenerator;

    @Autowired
    private EntityManager entityManager;

    private Connection connection;

    public UserDaoImpl() {
    }

    @Override
    @Transactional
    public boolean addUser(User user) {

        if (getUserByLogin(user.getLogin()) != null) return false;
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.detach(user);
        entityManager.close();

        if (getUserByLogin(user.getLogin()) == null) return false;
        return true;
    }

    @Override
    public User getUserByLogin(String login) {

        Query query = entityManager.createQuery("SELECT c FROM User c", User.class);
        // Костыль, потому что не могу достать значение по логину, хотя, вроде делал всё правильно
        User findUser = null;
        ArrayList<User> users = (ArrayList<User>) query.getResultList();
        for (User u: users) {
            if (u.getLogin().equals(login)) {
                findUser = u;
                break;
            }
        }
        return findUser;
    }
}
