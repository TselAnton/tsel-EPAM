package dao;

import model.impl.User;

public interface UserDao {

    boolean addUser(User user);

    User getUserByLogin(String login);
}
