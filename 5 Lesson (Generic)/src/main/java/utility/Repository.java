package utility;

import convector.ConvectorToUsers;
import entities.User;
import interfaces.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Сервир, реализующий работу с обычным User
 */
public class Repository implements Service<User> {

    private final ConvectorToUsers convector = new ConvectorToUsers();

    @Override
    public User getEntity() {
        return User.generateUser();
    }

    @Override
    public List<User> getAllEnitys() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(5) + 1; i++) {
            users.add(User.generateUser());
        }
        return users;
    }

    @Override
    public void saveEnity(User enity) {
        System.out.println(enity.toString());
    }

    @Override
    public void saveMultipleEnitys(List<User> enitys) {
        for(User enity : enitys) {
           System.out.println(enity.toString());
        }
    }
}
