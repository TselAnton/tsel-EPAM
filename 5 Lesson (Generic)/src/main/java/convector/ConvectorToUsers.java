package convector;

import dto.UserDto;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class ConvectorToUsers implements Convector<UserDto, User> {
    @Override
    public User conOneEnity(UserDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getBirthDate(), dto.getAdress().getCountry(),
                dto.getAdress().getCity(), dto.getAdress().getStreet(), dto.getAdress().getHouseNum(),
                dto.getAdress().getApartNum());
    }

    @Override
    public List<User> conManyEnities(List<UserDto> dto) {
        List<User> users = new ArrayList<>();
        for (UserDto userDto : dto) {
            users.add(conOneEnity(userDto));
        }
        return users;
    }
}
