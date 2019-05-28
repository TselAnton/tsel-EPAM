package convector.impl.dto_to_entity;

import convector.Convector;
import dto.UserDto;
import generator.entity.UserGenerator;
import model.impl.User;
import org.springframework.beans.factory.annotation.Autowired;

public class DtoToUserConvector implements Convector<User, UserDto> {

    @Autowired
    private UserGenerator userGenerator;

    @Override
    public User convert(UserDto dto) {
        if (dto == null) return null;
        User user = userGenerator.generateNewUser();

        user.setId(dto.getId());
        user.setFio(dto.getFio());
        user.setLogin(dto.getLogin());
        user.setPassword("");
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setCity(dto.getCity());
        user.setStreet(dto.getStreet());
        user.setHouse(dto.getHouse());
        user.setApartment(dto.getApartment());
        user.setRegistrationDate(dto.getRegistrationDate());
        user.setRoleId(dto.getRoleId());
        user.setQtyOrders(dto.getQtyOrders());

        return user;
    }
}
