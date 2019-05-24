package view.impl.extend;

import exeptions.InvalidInputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import utils.ViewUtils;
import view.impl.ViewAbstract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * View входа в систему. Имеет методы для заполнения полей входа и регистрации
 */
public class SingInView extends ViewAbstract {

    private final Scanner SCANNER = new Scanner(System.in);
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));   // Для ввода пустой строки
    private final Logger LOGGER = LoggerFactory.getLogger(SingInView.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");


    public SingInView(int level) {super(level);}

    @Override
    public int showMenu() {
        System.out.println("==== Вход в магазин ====");
        System.out.println("1. Вход при помощи логина и пароля");
        System.out.println("2. Регистрация нового пользователя");
        System.out.println("3. Выход из программы");
        int pointCount = 3;

        return ViewUtils.getMenuPoint(SCANNER, pointCount, LOGGER, MARKER);
    }

    /**
     * Форма для входа через логин и пароль
     * @return Массив значений логин-пароль
     */
    public String[] logIn() {

        String login, password;
        System.out.println("==== Вход при помощи логина и пароля ====");

        login = inputLogin();
        password = inputPassword();

        System.out.println();
        return new String[]{login, password};
    }

    /**
     * Регистрация пользователя
     * @return Все поля пользователя
     */
    public String[] registration() {

        String fio, login, password, phone, email, city, steet, house, appart;

        System.out.println("==== Регистрация нового пользователя ====");
        System.out.println("Обязательные поля отмечены звёздочкой — *");

        fio = inputFio();
        login = inputLogin();
        password = inputPassword();
        phone = inputPhone();
        email = inputEmail();
        city = inputCity();
        steet = inputStreet();
        house = inputHouse();
        appart = inputApart();

        System.out.println();
        return new String[]{fio, login, password, phone, email, city, steet, house, appart};
    }
    
    private String inputLogin() {
        boolean checkInvalidInput = false;
        String login = "";
        
        while (!checkInvalidInput) {
            System.out.print("Введите логин: ");
            login = SCANNER.next();

            try {
                if (!Pattern.compile("[A-Za-z][A-Za-z0-9_]{3,}").matcher(login).matches()) {
                    throw new InvalidInputFormat("login: " + login);
                } else {
                    checkInvalidInput = true;
                }
            } catch (InvalidInputFormat e) {
                LOGGER.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат логина! Логин должен состоять только из английских букв, цифр," +
                        "и знака нижнего подчёркивания!\nТак же логин должен начинаться с буквы и быть размером " +
                        "не меньше трёх символов!\nПовторите ввод!\n");
            }
        }
        return login;
    }

    private String inputPassword() {
        boolean checkInvalidInput = false;
        String password = "";

        while (!checkInvalidInput) {
            System.out.print("Введите пароль: ");
            password = SCANNER.next();

            try {
                if (!Pattern.compile("[A-Za-z0-9а-яА-Я]{3,}").matcher(password).matches()) {
                    throw new InvalidInputFormat("Wrong format of password");
                } else {
                    checkInvalidInput = true;
                }
            } catch (InvalidInputFormat e) {
                LOGGER.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат пароля! Пароль должен состоять только из букв, цифр!" +
                        "\nТак же пароль должен иметь размер не меньше трёх символов!\nПовторите ввод!\n");
            }
        }
        return password;
    }

    private String inputFio() {
        boolean checkInvalidInput = false;
        String fio = "";

        while (!checkInvalidInput) {
            System.out.print(" Введите ФИО: ");

            try {
                try {
                    fio = READER.readLine();
                } catch (IOException e) {
                    throw new InvalidInputFormat(e);
                }

                if (!Pattern.compile("[А-Я][а-я]{3,} [А-Я][а-я]+ [А-Я][а-я]{4,}").matcher(fio).matches()
                        && !fio.equals("")) {
                    throw new InvalidInputFormat("Fio: " + fio);
                } else {
                    checkInvalidInput = true;
                }
            } catch (InvalidInputFormat e) {
                LOGGER.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат ФИО! Фамилия, имя и отчество должны начинаться с заглавной " +
                        "буквы, быть написаны через пробел и содержать в себе только русские буквы!" +
                        "\nПовторите ввод!\n");
            }
        }
        return fio;
    }

    private String inputPhone() {
        boolean checkInvalidInput = false;
        String phone = "";

        while (!checkInvalidInput) {
            System.out.print("*Введите телефон: +7");
            phone = SCANNER.next();

            try {
                if (!Pattern.compile("^[0-9]{10}$").matcher(phone).matches()) {
                    throw new InvalidInputFormat("Phone: " + phone);
                } else {
                    phone = "8" + phone;
                    checkInvalidInput = true;
                }
            } catch (InvalidInputFormat e) {
                LOGGER.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат телефона! Ввести телефон необходимо без " +
                        "восьмёрки в начале!\nПовторите ввод!\n");
            }
        }
        return phone;
    }

    private String inputEmail() {
        boolean checkInvalidInput = false;
        String email = "";

        while (!checkInvalidInput) {
            System.out.print("*Введите e-mail: ");
            email = SCANNER.next();

            try {
                if (!Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
                        .matcher(email).matches()) {
                    throw new InvalidInputFormat("Email: " + email);
                } else {
                    checkInvalidInput = true;
                }
            } catch (InvalidInputFormat e) {
                LOGGER.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат e-mail! Проверьте правильность почтового " +
                        "ящика и повторите ввод!\n");
            }
        }
        return email;
    }

    private String inputCity() {
        boolean checkInvalidInput = false;
        String city = "";

        while (!checkInvalidInput) {
            System.out.print("*Введите город: ");
            city = SCANNER.next();

            try {
                if (!Pattern.compile("^[А-Яа-я]{3,}$").matcher(city).matches()) {
                    throw new InvalidInputFormat("City: " + city);
                } else {
                    city = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();
                    checkInvalidInput = true;
                }
            } catch (InvalidInputFormat e) {
                LOGGER.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат города! Длина названия города должне быть не менее трёх " +
                        "символов!\nПовторите ввод!\n");
            }
        }
        return city;
    }

    private String inputStreet() {
        boolean checkInvalidInput = false;
        String steet = "";

        while (!checkInvalidInput) {

            checkInvalidInput = true;
            System.out.print(" Введите улицу: ");

            try {
                try {
                    steet = READER.readLine();
                } catch (IOException e) {
                    checkInvalidInput = false;
                    throw new InvalidInputFormat(e);
                }
            } catch (InvalidInputFormat e) {
                LOGGER.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат улицы! Повторите ввод!\n");
            }
        }
        return steet;
    }

    private String inputHouse() {
        boolean checkInvalidInput = false;
        String house = "";

        while (!checkInvalidInput) {
            System.out.print(" Введите номер дома: ");

            try {
                try {
                    house = READER.readLine();
                } catch (IOException e) {
                    throw new InvalidInputFormat(e);
                }

                if (!Pattern.compile("^[0-9]+[а-я]?$").matcher(house).matches()
                        && !house.equals("")) {
                    throw new InvalidInputFormat("House: " + house);
                } else {
                    checkInvalidInput = true;
                }
            } catch (InvalidInputFormat e) {
                LOGGER.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат номера дома! Повторите ввод!\n");
            }
        }
        return house;
    }

    private String inputApart() {
        boolean checkInvalidInput = false;
        String appart = "";

        while (!checkInvalidInput) {
            System.out.print(" Введите номер квартиры: ");

            try {
                try {
                    appart = READER.readLine();
                } catch (IOException e) {
                    throw new InvalidInputFormat(e);
                }

                if (!Pattern.compile("^[0-9]+$").matcher(appart).matches()
                        && !appart.equals("")) {
                    throw new InvalidInputFormat("Apartment: " + appart);
                } else {
                    checkInvalidInput = true;
                }
            } catch (InvalidInputFormat e) {
                LOGGER.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат номера квартиры! Повторите ввод!\n");
            }
        }
        return appart;
    }
}
