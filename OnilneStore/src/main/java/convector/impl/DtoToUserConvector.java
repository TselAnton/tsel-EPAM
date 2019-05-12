package convector.impl;

import convector.Convector;
import dto.UserDto;
import entity.User;

public class DtoToUserConvector implements Convector<User, UserDto> {

    @Override
    public User convert(UserDto dto) {
        if (dto == null) return null;

        return new User(
                dto.getId(),
                dto.getFio(),
                dto.getLogin(),
                "",
                dto.getPhone(),
                dto.getEmail(),
                dto.getCity(),
                dto.getStreet(),
                dto.getHouse(),
                dto.getApartment(),
                dto.getRegistrationDate(),
                dto.getRoleId(),
                dto.getQtyOrders()
        );
    }
}
