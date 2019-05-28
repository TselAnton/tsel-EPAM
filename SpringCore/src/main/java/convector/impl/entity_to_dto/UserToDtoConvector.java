package convector.impl.entity_to_dto;

import convector.Convector;
import dto.UserDto;
import generator.dto.UserDtoGenerator;
import model.impl.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserToDtoConvector implements Convector<UserDto, User> {

    @Autowired
    private UserDtoGenerator userDtoGenerator;

    @Override
    public UserDto convert(User obj) {

        if (obj == null) return null;

        UserDto userDto = userDtoGenerator.generateNewUserDto();
        userDto.setId(obj.getId());
        userDto.setFio(obj.getFio());
        userDto.setLogin(obj.getLogin());
        userDto.setPhone(obj.getPhone());
        userDto.setEmail(obj.getEmail());
        userDto.setCity(obj.getCity());
        userDto.setStreet(obj.getStreet());
        userDto.setHouse(obj.getHouse());
        userDto.setApartment(obj.getApartment());
        userDto.setRegistrationDate(obj.getRegistrationDate());
        userDto.setRoleId(obj.getRoleId());
        userDto.setQtyOrders(obj.getQtyOrders());
        userDto.setOrderDto(null);

        return userDto;
    }
}
