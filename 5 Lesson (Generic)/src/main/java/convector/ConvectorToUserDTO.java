package convector;

import dto.UserDto;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class ConvectorToUserDTO implements Convector<User, UserDto> {
    @Override
    public UserDto conOneEnity(User dto) {
        return new UserDto(dto.getId(), dto.getName(), dto.getBirthDate(), dto.getAdress().getCountry(),
                dto.getAdress().getCity(), dto.getAdress().getStreet(), dto.getAdress().getHouseNum(),
                dto.getAdress().getApartNum());
    }

    @Override
    public List<UserDto> conManyEnities(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(conOneEnity(user));
        }
        return userDtos;
    }
}
