package view.impl.extend;

import dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import utils.ViewUtils;
import view.impl.ViewAbstract;

import java.util.List;
import java.util.Scanner;

public class ShoppingCartView extends ViewAbstract {

    private final Scanner SCANNER = new Scanner(System.in);
    private final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartView.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");

    public ShoppingCartView(int level) {
        super(level);
    }

    @Override
    public int showMenu() {
        System.out.println("==== Корзина товаров ====");
        System.out.println("1. Просмотр корзины");
        System.out.println("2. Оплатить товар");
        System.out.println("3. Вернуться в основное меню");
        int pointCount = 3;

        return ViewUtils.getMenuPoint(SCANNER, pointCount, LOGGER, MARKER);
    }

    public void showShoppingCart(List<ProductDto> products) {
        System.out.println("==== Ваша корзина товаров ====");
        if (products.size() != 0) {
            for (int i = 0; i < products.size(); i++) {
                System.out.println(String.format("%4d. ", i + 1) + products.get(i).toShopCartString());
            }
        } else {
            System.out.println("В вашей корзине пока нет товаров!");
        }
        System.out.println();
    }
}
