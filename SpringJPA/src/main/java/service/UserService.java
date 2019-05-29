package service;

import convector.impl.entity_to_dto.UserToDtoConvector;
import dao.impl.UserDaoImpl;
import dto.UserDto;
import generator.entity.UserGenerator;
import model.impl.User;
import org.springframework.beans.factory.annotation.Autowired;

public final class UserService {

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Autowired
    private UserGenerator userGenerator;

    @Autowired
    private UserToDtoConvector userToDtoConvector;

    public UserDto logIn(String[] args) {
        UserDto userDto = null;
        User user = userDaoImpl.getUserByLogin(args[0]);

        if (user != null && user.getPassword().equals(args[1])) {
            userDto = userToDtoConvector.convert(user);
        }

        return userDto;
    }

    public UserDto registrationNewUser(String[] args) {
        User user = userGenerator.generateNewUser();

        user.setFio(args[0]);
        user.setLogin(args[1]);
        user.setPassword(args[2]);
        user.setPhone(args[3]);
        user.setEmail(args[4]);
        user.setCity(args[5]);
        user.setStreet(args[6]);
        user.setHouse(args[7]);
        user.setApartment(args[8]);
        user.setRoleId(2);

        boolean result = userDaoImpl.addUser(user);

        UserDto userDto = null;
        if (result) {
            userDto = logIn(new String[]{user.getLogin(), user.getPassword()});
        }
        return userDto;
    }
}
