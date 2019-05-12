package view.impl.extend;

import exeptions.InvalidInputFormat;
import exeptions.NotPointFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import view.impl.ViewAbstract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * View входа в систему. Имеет методы для заполнения полей входа и регистрации
 *
 * Level 0
 */
public class SingInView extends ViewAbstract {

    private Scanner scanner = new Scanner(System.in);
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));   // Для ввода пустой строки

    private final Logger logger = LoggerFactory.getLogger(SingInView.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");


    public SingInView(int level) {super(level);}

    @Override
    @SuppressWarnings("Duplicates")
    public int showMenu() {
        System.out.println("==== Вход в магазин ====");
        System.out.println("1. Вход при помощи логина и пароля");
        System.out.println("2. Регистрация нового пользователя");
        System.out.println("3. Выход из программы");
        int pointCount = 3;

        System.out.print("Выберете пункт меню: ");
        int point = 0;

        try {
            try {
                point = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                throw new NotPointFound(e);
            } finally {
                if (point != 0 && (point > pointCount || point < 1)) {
                    throw new NotPointFound(point);
                }
            }
        } catch (NotPointFound e) {
            logger.error(MARKER, "NotPointFound", e);
            System.out.println("Не верно указан пункт меню!");
        }

        System.out.println();
        return point;
    }

    /**
     * Форма для входа через логин и пароль
     * @return Массив значений логин-пароль
     */
    @SuppressWarnings("Duplicates")
    public String[] logIn() {

        String login = "", password = "";
        boolean check = false;

        System.out.println("==== Вход при помощи логина и пароля ====");

        while (!check) {
            System.out.print("Введите логин: ");
            login = scanner.next();

            try {
                if (!Pattern.compile("[A-Za-z][A-Za-z0-9_]{3,}").matcher(login).matches()) {
                    throw new InvalidInputFormat("login: " + login);
                } else {
                    check = true;
                }
            } catch (InvalidInputFormat e) {
                logger.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат логина! Логин должен состоять только из английских букв, цифр," +
                        "и знака нижнего подчёркивания!\nТак же логин должен начинаться с буквы и быть размером " +
                        "не меньше трёх символов!\nПовторите ввод!\n");
            }

        }

        check = false;
        while (!check) {
            System.out.print("Введите пароль: ");
            password = scanner.next();

            try {
                if (!Pattern.compile("[A-Za-z0-9а-яА-Я]{3,}").matcher(password).matches()) {
                    throw new InvalidInputFormat("Wrong format of password");
                } else {
                    check = true;
                }
            } catch (InvalidInputFormat e) {
                logger.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат пароля! Пароль должен состоять только из букв, цифр!" +
                        "\nТак же пароль должен иметь размер не меньше трёх символов!\nПовторите ввод!\n");
            }
        }

        System.out.println();
        return new String[]{login, password};
    }

    /**
     * Регистрация пользователя
     * @return Все поля пользователя, либо экзепляр UserDto
     */
    @SuppressWarnings("Duplicates")
    public String[] registration() {

        String fio = "", login = "", password = "", phone = "", email = "", city = "", steet = "", house = "", appart = "";
        boolean check = false;

        System.out.println("==== Регистрация нового пользователя ====");
        System.out.println("Обязательные поля отмечены звёздочкой — *");

        /* Ввод ФИО */
        while (!check) {
            System.out.print(" Введите ФИО: ");

            try {
                try {
                    fio = reader.readLine();
                } catch (IOException e) {
                    throw new InvalidInputFormat(e);
                }

                if (!Pattern.compile("[А-Я][а-я]{3,} [А-Я][а-я]+ [А-Я][а-я]{4,}").matcher(fio).matches()
                        && !fio.equals("")) {
                    throw new InvalidInputFormat("Fio: " + fio);
                } else {
                    check = true;
                }
            } catch (InvalidInputFormat e) {
                logger.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат ФИО! Фамилия, имя и отчество должны начинаться с заглавной " +
                        "буквы, быть написаны через пробел и содержать в себе только русские буквы!" +
                        "\nПовторите ввод!\n");
            }
        }
        check = false;

        /* Ввод логина */
        while (!check) {
            System.out.print("*Введите логин: ");
            login = scanner.next();

            try {
                if (!Pattern.compile("[A-Za-z][A-Za-z0-9_]{3,}").matcher(login).matches()) {
                    throw new InvalidInputFormat("Login: " + login);
                } else {
                    check = true;
                }
            } catch (InvalidInputFormat e) {
                logger.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат логина! Логин должен состоять только из английских букв, цифр " +
                        "и знака нижнего подчёркивания!\nТак же логин должен начинаться с буквы и быть размером " +
                        "не меньше трёх символов!\nПовторите ввод!\n");
            }
        }
        check = false;

        /* Ввод пароля */
        while (!check) {
            System.out.print("*Введите пароль: ");
            password = scanner.next();

            try {
                if (!Pattern.compile("[A-Za-z0-9а-яА-Я]{3,}").matcher(password).matches()) {
                    throw new InvalidInputFormat("Wrong format of password");
                } else {
                    check = true;
                }
            } catch (InvalidInputFormat e) {
                logger.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат пароля! Пароль должен состоять только из " +
                        "букв и цифр и длина его должна быть не менее трёх символов!\nПовторите ввод!\n");
            }
        }
        check = false;

        /* Ввод телефона */
        while (!check) {
            System.out.print("*Введите телефон: +7");
            phone = scanner.next();

            try {
                if (!Pattern.compile("^[0-9]{10}$").matcher(phone).matches()) {
                    throw new InvalidInputFormat("Phone: " + phone);
                } else {
                    phone = "8" + phone;
                    check = true;
                }
            } catch (InvalidInputFormat e) {
                logger.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат телефона! Ввести телефон необходимо без " +
                        "восьмёрки в начале!\nПовторите ввод!\n");
            }
        }
        check = false;

        /* Ввод email */
        while (!check) {
            System.out.print("*Введите e-mail: ");
            email = scanner.next();

            try {
                if (!Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
                        .matcher(email).matches()) {
                    throw new InvalidInputFormat("Email: " + email);
                } else {
                    check = true;
                }
            } catch (InvalidInputFormat e) {
                logger.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат e-mail! Проверьте правильность почтового " +
                        "ящика и повторите ввод!\n");
            }
        }
        check = false;

        /* Ввод города */
        while (!check) {
            System.out.print("*Введите город: ");
            city = scanner.next();

            try {
                if (!Pattern.compile("^[А-Яа-я]{3,}$").matcher(city).matches()) {
                    throw new InvalidInputFormat("City: " + city);
                } else {
                    city = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();
                    check = true;
                }
            } catch (InvalidInputFormat e) {
                logger.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат города! Длина названия города должне быть не менее трёх " +
                        "символов!\nПовторите ввод!\n");
            }
        }
        check = false;

        /* Ввод улицы */
        while (!check) {

            check = true;
            System.out.print(" Введите улицу: ");

            try {
                try {
                    steet = reader.readLine();
                } catch (IOException e) {
                    check = false;
                    throw new InvalidInputFormat(e);
                }
            } catch (InvalidInputFormat e) {
                logger.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат улицы! Повторите ввод!\n");
            }
        }
        check = false;

        /* Ввод дома */
        while (!check) {
            System.out.print(" Введите номер дома: ");

            try {
                try {
                    house = reader.readLine();
                } catch (IOException e) {
                    throw new InvalidInputFormat(e);
                }

                if (!Pattern.compile("^[0-9]+[а-я]?$").matcher(house).matches()
                        && !house.equals("")) {
                    throw new InvalidInputFormat("House: " + house);
                } else {
                    check = true;
                }
            } catch (InvalidInputFormat e) {
                logger.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат номера дома! Повторите ввод!\n");
            }
        }
        check = false;

        /* Ввод квартиры */
        while (!check) {
            System.out.print(" Введите номер квартиры: ");

            try {
                try {
                    appart = reader.readLine();
                } catch (IOException e) {
                    throw new InvalidInputFormat(e);
                }

                if (!Pattern.compile("^[0-9]+$").matcher(appart).matches()
                        && !appart.equals("")) {
                    throw new InvalidInputFormat("Apartment: " + appart);
                } else {
                    check = true;
                }
            } catch (InvalidInputFormat e) {
                logger.error(MARKER, "InvalidInputFormat", e);
                System.out.println("Неверный формат номера квартиры! Повторите ввод!\n");
            }
        }

        System.out.println();
        return new String[]{fio, login, password, phone, email, city, steet, house, appart};
    }
}
