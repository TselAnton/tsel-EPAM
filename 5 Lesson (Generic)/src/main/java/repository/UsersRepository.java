package repository;

import convector.ConvectorToUsers;
import entities.User;
import repository.inter.Repository;
import service.inter.Service;
import utilites.GeneratorEnities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Сервир, реализующий работу с обычным User
 */
public class UsersRepository implements Repository<User> {

    private final ConvectorToUsers convector = new ConvectorToUsers();
    private final GeneratorEnities generator = new GeneratorEnities();

    @Override
    public User getEntity() {
        return generator.generateUser();
    }

    @Override
    public List<User> getAllEnitys() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(5) + 1; i++) {
            users.add(generator.generateUser());
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
