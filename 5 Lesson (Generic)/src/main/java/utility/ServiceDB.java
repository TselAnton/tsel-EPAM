package utility;

import convector.ConvectorToUserDTO;
import convector.ConvectorToUsers;
import dto.UserDto;
import interfaces.Service;

import java.util.List;

/**
 * Сервис для БД и работы с UserDTO
 */
public class ServiceDB implements Service<UserDto> {

    private final Repository repository = new Repository();
    private final ConvectorToUserDTO convectorToUsersDTO = new ConvectorToUserDTO();
    private final ConvectorToUsers convectorToUsers = new ConvectorToUsers();

    @Override
    public UserDto getEntity() {
        return convectorToUsersDTO.conOneEnity(repository.getEntity());
    }

    @Override
    public List<UserDto> getAllEnitys() {
        return convectorToUsersDTO.conManyEnities(repository.getAllEnitys());
    }

    @Override
    public void saveEnity(UserDto enity) {
        repository.saveEnity(convectorToUsers.conOneEnity(enity));
    }

    @Override
    public void saveMultipleEnitys(List<UserDto> enitys) {
        repository.saveMultipleEnitys(convectorToUsers.conManyEnities(enitys));
    }


}
