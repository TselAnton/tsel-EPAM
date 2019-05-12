package dao;

import entity.User;

import java.util.List;

public interface UserDao {

    public boolean addUser(User user);
//    public boolean deleteUser(User user);
//    public boolean updateUser(User user);

    public User getUserById(int id);
    public User getUserByLogin(String login);

//    public List<User> getAllUsers();
}
