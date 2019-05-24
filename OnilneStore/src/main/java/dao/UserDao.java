package dao;

import model.User;

public interface UserDao {

    boolean addUser(User user);

    User getUserByLogin(String login);
}
