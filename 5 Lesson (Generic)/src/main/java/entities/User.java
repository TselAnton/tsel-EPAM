package entities;

import java.time.LocalDate;
import java.util.Random;

public class User {
    private long id;
    private String name;
    private LocalDate birthDate;
    private Adress adress;

    public User(long id, String name, LocalDate birthDate,
                String country, String city, String street, int houseNum, int apartNum) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.adress = new Adress(country, city, street, houseNum, apartNum);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Adress getAdress() {
        return adress;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", adress=" + adress.toString() +
                '}';
    }

    public static User generateUser() {
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

    public class Adress {
        private String country;
        private String city;
        private String street;
        private int houseNum;
        private int apartNum;

        public Adress(String country, String city, String street, int houseNum, int apartNum) {
            this.country = country;
            this.city = city;
            this.street = street;
            this.houseNum = houseNum;
            this.apartNum = apartNum;
        }

        public String getCountry() {
            return country;
        }

        public String getCity() {
            return city;
        }

        public String getStreet() {
            return street;
        }

        public int getHouseNum() {
            return houseNum;
        }

        public int getApartNum() {
            return apartNum;
        }

        @Override
        public String toString() {
            return "Adress{" +
                    "country='" + country + '\'' +
                    ", city='" + city + '\'' +
                    ", street='" + street + '\'' +
                    ", houseNum=" + houseNum +
                    ", apartNum=" + apartNum +
                    '}';
        }
    }
}
