package convector;

import dto.UserDto;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class ConvectorUsers implements Convector<UserDto, User> {
    @Override
    public User dtoToUser(UserDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getBirthDate(), dto.getAdress().getCountry(),
                dto.getAdress().getCity(), dto.getAdress().getStreet(), dto.getAdress().getHouseNum(),
                dto.getAdress().getApartNum());
    }

    @Override
    public UserDto userToDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getBirthDate(),
                user.getAdress().getCountry(), user.getAdress().getCity(), user.getAdress().getStreet(),
                user.getAdress().getHouseNum(), user.getAdress().getApartNum());
    }

    @Override
    public List<User> dtoListToUserList(List<UserDto> dto) {
        List<User> users = new ArrayList<>();
        for (UserDto userDto : dto) {
            users.add(dtoToUser(userDto));
        }
        return users;
    }

    @Override
    public List<UserDto> userListToDtoList(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(userToDto(user));
        }
        return userDtos;
    }


}
