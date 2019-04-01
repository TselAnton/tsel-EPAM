package utility;

import convector.ConvectorUsers;
import dto.UserDto;
import interfaces.Service;

import java.util.List;

/**
 * Сервис для БД и работы с UserDTO
 */
public class ServiceDB implements Service<UserDto> {

    private final ServiceLocal serviceLocal = new ServiceLocal();
    private final ConvectorUsers convector = new ConvectorUsers();

    @Override
    public UserDto getEntity() {
        return convector.userToDto(serviceLocal.getEntity());
    }

    @Override
    public List<UserDto> getAllEnitys() {
        return convector.userListToDtoList(serviceLocal.getAllEnitys());
    }

    @Override
    public void saveEnity(UserDto enity) {
        serviceLocal.saveEnity(convector.dtoToUser(enity));
    }

    @Override
    public void saveMultipleEnitys(List<UserDto> enitys) {
        serviceLocal.saveMultipleEnitys(convector.dtoListToUserList(enitys));
    }


}
