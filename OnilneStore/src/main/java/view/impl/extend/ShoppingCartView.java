package view.impl.extend;

import dto.ProductDto;
import exeptions.NotPointFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import view.impl.ViewAbstract;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Просмотр корзины
 *
 * Level 3
 */
public class ShoppingCartView extends ViewAbstract {

    private Scanner scanner = new Scanner(System.in);

    private final Logger logger = LoggerFactory.getLogger(ShoppingCartView.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");

    public ShoppingCartView(int level) {
        super(level);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public int showMenu() {
        System.out.println("==== Корзина товаров ====");
        System.out.println("1. Просмотр корзины");
        System.out.println("2. Оплатить товар");
        System.out.println("3. Вернуться в основное меню");
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

    public void showShoppingCart(List<ProductDto> products) {
        System.out.println("==== Ваша корзина товаров ====");
        if (products.size() != 0) {
            for (int i = 0; i < products.size(); i++) {
                System.out.println(String.format("%4d. ", i + 1) + products.get(i).toStringShopCart());
            }
        } else {
            System.out.println("В вашей корзине пока нет товаров!");
        }
        System.out.println();
    }
}
