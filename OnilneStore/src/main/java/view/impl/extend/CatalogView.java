package view.impl.extend;

import dto.ProductDto;
import entity.Category;
import exeptions.NotPointFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import view.impl.ViewAbstract;

import java.util.*;

/**
 * Просмотр каталога товаров
 *
 * Level 2
 */
public class CatalogView extends ViewAbstract {

    private Scanner scanner = new Scanner(System.in);

    private final Logger logger = LoggerFactory.getLogger(CatalogView.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("Exception ");

    private int page = 0;
    private int pageStep = 5;   // Кол-во показываемых товаров на странице
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
    @SuppressWarnings("Duplicates")
    public int showMenu() {
        System.out.println("==== Просмотр каталога товаров ====");

        showShortCatalog();

        System.out.println("1. Вперёд");
        System.out.println("2. Назад");
        System.out.println("3. Просмотр товара");
        System.out.println("4. Добавить товар в корзину");
        System.out.println("5. Вернуться в главное меню");
        int pointCount = 5;

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

    public void resetPage() {
        page = 0;
    }

    public void pageUp() {
        if (page + pageStep < products.size()) {
            page += pageStep;
        }
    }

    public void pageDown() {
        if (page - pageStep > 0) {
            page -= pageStep;
        } else {
            page = 0;
        }
    }

    public void showShortCatalog() {
        int sizeOfList = products.size();
        for (int i = page; i < page + pageStep; i++) {
            if (i < sizeOfList) {
                System.out.println(String.format("%3s. ", i + 1) + products.get(i).shortToString());
            }
        }
        System.out.println();
    }

    public void showProduct(int id, HashMap<Integer, Category> category) {
        logger.debug("ShowProduct: id = " + id);
        System.out.println("==== Просмотр товара ====");
        System.out.println(products.get(id - 1).toString(category));
        System.out.println();
    }

    @SuppressWarnings("Duplicates")
    public int getIdOfProduct() {
        System.out.print("Введите номер просматреваемого товара: ");
        int number = -1;

        try {
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                throw new NotPointFound(e);
            } finally {
                if (number != -1 && (number > products.size() || number < 1)) {
                    number = 0;
                    throw new NotPointFound(number);
                }
            }
        } catch (NotPointFound e) {
            logger.error(MARKER, "NotPointFound", e);
            System.out.println("Такого товара не существует!");
        }

        System.out.println();
        return number;
    }

    @SuppressWarnings("Duplicates")
    public ProductDto getProduct() {
        System.out.print("Введите номер товара для добавления в корзину: ");
        int number = -1;

        try {
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                throw new NotPointFound(e);
            } finally {
                if (number != -1 && (number > products.size() || number < 1)) {
                    number = 0;
                    throw new NotPointFound(number);
                }
            }
        } catch (NotPointFound e) {
            logger.error(MARKER, "NotPointFound", e);
            System.out.println("Такого товара не существует!");
        }

        return number != 0 ? products.get(number - 1) : null;
    }
}
