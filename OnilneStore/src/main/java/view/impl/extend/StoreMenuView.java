package view.impl.extend;

import dto.OrderDto;
import entity.OrderStatus;
import exeptions.NotPointFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import view.impl.ViewAbstract;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Основное меню
 *
 * Level 1
 */
public class StoreMenuView extends ViewAbstract {

    private Scanner scanner = new Scanner(System.in);

    private final Logger logger = LoggerFactory.getLogger(StoreMenuView.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");


    public StoreMenuView(int level) {super(level);}

    @Override
    @SuppressWarnings("Duplicates")
    public int showMenu() {
        System.out.println("==== Основное меню ====");
        System.out.println("1. Просмотр каталога товаров"); // +
        System.out.println("2. Ваша корзина");
        System.out.println("3. Просмотр личного профиля");  // +
        System.out.println("4. Просмотр всех заказов");
        System.out.println("5. Админ панель");
        System.out.println("6. Выход из аккаунта");     // +
        System.out.println("7. Выход из программы");    // +
        int pointCount = 7;

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


    public void showUserProfile(String profileInfo) {
        System.out.println("==== Профиль пользователя ====");
        System.out.println(profileInfo);
        System.out.println();
    }

    public void showUserOrders(List<OrderDto> orders, HashMap<Integer, OrderStatus> statuses) {
        System.out.println("==== Просмотр ваших заказов ====");
        if (orders.size() == 0) {
            System.out.println("У вас пока нет заказов!");
        } else {
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getStatusId() != 1) {
                    System.out.println(String.format("%4s. %s", (i+1), orders.get(i).toString(statuses)));
                }
            }
        }
        System.out.println();
    }
}
