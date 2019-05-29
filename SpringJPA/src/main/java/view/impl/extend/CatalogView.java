package view.impl.extend;

import dto.ProductDto;
import generator.collection.HashMapGenerator;
import generator.utils.ScannerGenerator;
import model.impl.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import utils.ViewUtils;
import view.impl.ViewAbstract;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Просмотр каталога товаров
 */
public class CatalogView extends ViewAbstract {

    @Autowired
    @Qualifier("productDtoHashMap")
    private HashMapGenerator hashMapGenerator;

    @Autowired
    private ViewUtils viewUtils;

    private Scanner scanner;

    private final Logger LOGGER = LoggerFactory.getLogger(CatalogView.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");

    private int visibleEntries = 0;
    private int numberOfVisibleEntries = 5;   
    private HashMap<Integer, ProductDto> products;

    public CatalogView(int level, ScannerGenerator scannerGenerator) {
        super(level);
        scanner = scannerGenerator.generateScanner();
    }

    public void setProducts(List<ProductDto> products) {
        this.products = hashMapGenerator.generateHashMap();
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

        return viewUtils.getMenuPoint(scanner, pointCount, LOGGER, MARKER);
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
        int number = viewUtils.inputNumber(scanner, products.size(), LOGGER, MARKER);
        System.out.println();
        return number;
    }

    public ProductDto getProduct() {
        System.out.print("Введите номер товара для добавления в корзину: ");
        int number = viewUtils.inputNumber(scanner, products.size(), LOGGER, MARKER);
        return number != 0 ? products.get(number - 1) : null;
    }
}
