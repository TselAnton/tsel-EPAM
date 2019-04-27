import dto.UserDto;
import service.ServiceDB;
import utilites.GeneratorEnities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        ServiceDB serviceDB = new ServiceDB();
        GeneratorEnities generator = new GeneratorEnities();

        List<UserDto> usersDto = serviceDB.getAllEnitys();
        for (int i = 0; i <= new Random().nextInt(5) + 1; i++) {
            usersDto.add(generator.generateUserDto());
        }

        System.out.println("Came alone DTO");
        serviceDB.saveEnity(generator.generateUserDto()); // Добавляем одну дтошку из вне
        System.out.println("\nCame some DTO");
        serviceDB.saveMultipleEnitys(usersDto); // Добавляем несколько новых дтошек из вне

        Collections.sort(usersDto, Comparator.comparing(UserDto::getId));   // Сортируем по ID
        System.out.println("\nSorted by ID");
        serviceDB.saveMultipleEnitys(usersDto);

        Collections.sort(usersDto, Comparator.comparing(UserDto::getBirthDate).reversed()); // Сортируем по дню рождению
        System.out.println("\nSorted in reverse order by birthday");
        serviceDB.saveMultipleEnitys(usersDto);

        Collections.sort(usersDto, Comparator.comparing(userDto -> userDto.getAdress().getCountry()));  // Сортируем по стране
        System.out.println("\nSorted by country");
        serviceDB.saveMultipleEnitys(usersDto);
    }
}
