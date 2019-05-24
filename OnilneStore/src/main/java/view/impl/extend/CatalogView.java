package view.impl.extend;

import dto.ProductDto;
import model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import utils.ViewUtils;
import view.impl.ViewAbstract;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Просмотр каталога товаров
 */
public class CatalogView extends ViewAbstract {

    private final Scanner SCANNER = new Scanner(System.in);
    private final Logger LOGGER = LoggerFactory.getLogger(CatalogView.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");

    private int visibleEntries = 0;
    private int numberOfVisibleEntries = 5;   
    private HashMap<Integer, ProductDto> products = new HashMap<>();

    public CatalogView(int level) {
        super(level);
    }

    public void setProducts(List<ProductDto> products) {
        for (int i = 0; i < products.size(); i++) {
            this.products.put(i, products.get(i));
        }
    }

    @Override
    public int showMenu() {
        System.out.println("==== Просмотр каталога товаров ====");

        showShortCatalog();

        System.out.println("1. Вперёд");
        System.out.println("2. Назад");
        System.out.println("3. Просмотр товара");
        System.out.println("4. Добавить товар в корзину");
        System.out.println("5. Вернуться в главное меню");
        int pointCount = 5;

        return ViewUtils.getMenuPoint(SCANNER, pointCount, LOGGER, MARKER);
    }

    public void resetPage() {
        visibleEntries = 0;
    }

    public void pageUp() {
        if (visibleEntries + numberOfVisibleEntries < products.size()) {
            visibleEntries += numberOfVisibleEntries;
        }
    }

    public void pageDown() {
        if (visibleEntries - numberOfVisibleEntries > 0) {
            visibleEntries -= numberOfVisibleEntries;
        } else {
            visibleEntries = 0;
        }
    }

    public void showShortCatalog() {
        int sizeOfList = products.size();
        for (int i = visibleEntries; i < visibleEntries + numberOfVisibleEntries; i++) {
            if (i < sizeOfList) {
                System.out.println(String.format("%3s. ", i + 1) + products.get(i).toShortString());
            }
        }
        System.out.println();
    }

    public void showProduct(int id, HashMap<Integer, Category> category) {
        LOGGER.debug("ShowProduct: id = " + id);
        System.out.println("==== Просмотр товара ====");
        System.out.println(products.get(id - 1).toString(category));
        System.out.println();
    }

    public int getIdOfProduct() {
        System.out.print("Введите номер просматреваемого товара: ");
        int number = ViewUtils.inputNumber(SCANNER, products.size(), LOGGER, MARKER);
        System.out.println();
        return number;
    }

    public ProductDto getProduct() {
        System.out.print("Введите номер товара для добавления в корзину: ");
        int number = ViewUtils.inputNumber(SCANNER, products.size(), LOGGER, MARKER);
        return number != 0 ? products.get(number - 1) : null;
    }


}
