package service;

import convector.ConvectorToUserDTO;
import convector.ConvectorToUsers;
import dto.UserDto;
import repository.UsersRepository;
import service.inter.Service;

import java.util.List;

/**
 * Сервис для работы с БД и UserDTO
 */
public class ServiceDB implements Service<UserDto> {

    private final UsersRepository usersRepository = new UsersRepository();
    private final ConvectorToUserDTO convectorToUsersDTO = new ConvectorToUserDTO();
    private final ConvectorToUsers convectorToUsers = new ConvectorToUsers();

    @Override
    public UserDto getEntity() {
        return convectorToUsersDTO.conOneEnity(usersRepository.getEntity());
    }

    @Override
    public List<UserDto> getAllEnitys() {
        return convectorToUsersDTO.conManyEnities(usersRepository.getAllEnitys());
    }

    @Override
    public void saveEnity(UserDto enity) {
        usersRepository.saveEnity(convectorToUsers.conOneEnity(enity));
    }

    @Override
    public void saveMultipleEnitys(List<UserDto> enitys) {
        usersRepository.saveMultipleEnitys(convectorToUsers.conManyEnities(enitys));
    }


}
