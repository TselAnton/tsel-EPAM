package convector.impl;

import convector.Convector;
import dto.UserDto;
import entity.User;

public class UserToDtoConvector implements Convector<UserDto, User> {

    @Override
    public UserDto convert(User obj) {
        if (obj == null) return null;
        return new UserDto(
                obj.getId(),
                obj.getFio(),
                obj.getLogin(),
                obj.getPhone(),
                obj.getEmail(),
                obj.getCity(),
                obj.getStreet(),
                obj.getHouse(),
                obj.getApartment(),
                obj.getRegistrationDate(),
                obj.getRoleId(),
                obj.getQtyOrders(),
                null
        );
    }
}
