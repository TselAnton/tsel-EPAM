package utilites;

import dto.UserDto;
import entities.User;

import java.time.LocalDate;
import java.util.Random;

public class GeneratorEnities {
    public User generateUser() {
        String[] names = {"Владимир", "Пётр", "Алексей", "Евкакий", "Святозар"};
        String[] country = {"Россия", "Америка", "Польша", "Германия"};
        String[] cities = {"Москва", "Вашингтон", "Варшава", "Берлин"};
        String[] streets = {"Брянская", "8 Западная улица", "Маршалковская", "Фридрихштрассе"};
        Random r = new Random();
        int nameId = r.nextInt(names.length);
        int addressId = r.nextInt(country.length);

        return new User(r.nextInt(1000), names[nameId],
                LocalDate.of(r.nextInt(20) + 1980, r.nextInt(11) + 1,
                        r.nextInt(30) + 1), country[addressId], cities[addressId],
                streets[addressId],r.nextInt(98) + 1, r.nextInt(199) + 1);
    }

    public UserDto generateUserDto() {
        String[] names = {"Евгения", "Анастасия", "Елизавета", "Анна", "Мария"};
        String[] country = {"Чехия", "Грузия", "Украина", "Австралия"};
        String[] cities = {"Прага", "Тбилиси", "Киев", "Канберра"};
        String[] streets = {"Увоз", "Нато-Вачнадзе", "Крещатик", "Ноулс Плейс"};
        Random r = new Random();
        int nameId = r.nextInt(names.length);
        int addressId = r.nextInt(country.length);

        return new UserDto(r.nextInt(1000), names[nameId],
                LocalDate.of(r.nextInt(20) + 1980, r.nextInt(11) + 1,
                        r.nextInt(30) + 1), country[addressId], cities[addressId],
                streets[addressId],r.nextInt(98) + 1, r.nextInt(199) + 1);
    }
}
