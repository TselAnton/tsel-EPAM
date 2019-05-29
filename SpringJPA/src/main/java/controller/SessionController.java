package controller;

import dto.ProductDto;
import dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import service.OrderService;
import service.ProductService;
import service.ShopCartService;
import service.UserService;
import service.impl.extend.CategoryList;
import service.impl.extend.OrderStatusList;
import service.impl.extend.RoleList;
import view.impl.extend.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Класс для управление текущей сессией пользователя
 */
public final class SessionController {

    /* View */
    @Autowired
    private SingInView singInView;
    @Autowired
    private StoreMenuView storeMenuView;
    @Autowired
    private CatalogView catalogView ;
    @Autowired
    private AdminPanelView adminPanelView;
    @Autowired
    private ShoppingCartView shoppingCartView;

    /* Service */
    @Autowired
    private UserService userService;
    @Autowired
    private RoleList roleList;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryList categoryList;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    private OrderStatusList orderStatusList;

    private Connection connection;

    @Autowired
    private DataSource dataSource;

    private UserDto user = null;
    private int level = 0;

    private final Logger LOGGER = LoggerFactory.getLogger(SessionController.class.getName());
    private final Marker MARKER = MarkerFactory.getMarker("SQLException");

    public SessionController(ConnectionController connectionController) {
        connection = connectionController.getConnection();
    }

    public void runApp() {
        if (connectionIsGood()) {
            roleList.initialize();
            orderStatusList.initialize();
            categoryList.initialize();

            while (true) {
                if (user == null) {
                    singInViewShowMenu();

                } else if (level == storeMenuView.getLevel()) {
                    storeMenuViewShowMenu();

                } else if (level == catalogView.getLevel()) {
                    catalogViewShowMenu();

                } else if (level == adminPanelView.getLevel()) {
                    adminPanelViewShowMenu();

                } else if (level == shoppingCartView.getLevel()) {
                    shopingCartViewShowMenu();
                }
            }
        } else singInView.showMessage("Нет подключения!");
    }

    private boolean connectionIsGood() {
        try {
            LOGGER.debug("Connect = " + dataSource.getConnection().toString());
            if (dataSource.getConnection() != null) return true;
        } catch (SQLException e) {
            LOGGER.error(MARKER, "Can't get connection", e);
        }
        return false;
    }

    private void singInViewShowMenu() {
        int menuPoint = singInView.showMenu();
        switch (menuPoint) {
            case 1:
                signIn();
                break;
            case 2:
                registerUser();
                break;
            case 3:
                exitFromApp();
                break;
        }
    }

    private void storeMenuViewShowMenu() {
        int pointMenu = storeMenuView.showMenu();

        switch (pointMenu) {
            case 1:
                level = catalogView.getLevel();
                catalogView.setProducts(productService.getAllProducts());
                break;

            case 2:
                level = shoppingCartView.getLevel();
                break;

            case 3:
                storeMenuView.showUserProfile(user.toString(roleList.getAllNames()));
                break;

            case 4:
                storeMenuView.showUserOrders(orderService.getAllOrdersByUserId(user.getId()),
                        orderStatusList.getAllNames());
                break;

            case 5:
                if (user.getRoleId() == 1) {
                    level = adminPanelView.getLevel();
                } else {
                    storeMenuView.showMessage("У вас нет доступа к административной панели!");
                }
                break;

            case 6:
                user = null;
                level = singInView.getLevel();
                break;

            case 7:
                exitFromApp();
                break;
        }
    }

    private void catalogViewShowMenu() {
        int menuPoint = catalogView.showMenu();

        switch (menuPoint) {
            case 1:
                catalogView.pageUp();
                break;
            case 2:
                catalogView.pageDown();
                break;
            case 3:
                int numOfProduct = catalogView.getIdOfProduct();
                if (numOfProduct != 0) {
                    catalogView.showProduct(numOfProduct, categoryList.getAllNames());
                }
                break;
            case 4:
                ProductDto productDto = catalogView.getProduct();
                if (productDto != null) {
                    boolean result = shopCartService.addProductInShopCart(user.getOrderDto(), productDto);
                    if (result) {
                        catalogView.showMessage("Товера успешно добавлен в корзину!");
                    } else {
                        catalogView.showMessage("Не удалось добавить товар в корзину! Попробуйте ещё раз!");
                    }
                }
                break;
            case 5:
                catalogView.resetPage();
                level = storeMenuView.getLevel();
                break;
        }
    }

    private void adminPanelViewShowMenu() {
        int menuPoint = adminPanelView.showMenu();

        switch (menuPoint) {
            case 1:
                ProductDto dto = adminPanelView.setDiscount(productService.getAllProducts());
                if (dto != null) {
                    int result = productService.updateProductDiscount(dto);
                    if (result != 0) {
                        adminPanelView.showMessage("Скидка успешно изменена!");
                    } else {
                        adminPanelView.showMessage("Ошибка создания скидки!");
                    }
                }
                break;
            case 2:
                int count = productService.addProductsFromFile(adminPanelView.getProductsFromXml());
                adminPanelView.showMessage("Было изменено записей: " + count);
                break;
            case 3:
                level = storeMenuView.getLevel();
                break;
        }
    }

    private void shopingCartViewShowMenu() {
        int menuPoint = shoppingCartView.showMenu();

        switch (menuPoint) {
            case 1:
                shoppingCartView.showShoppingCart(shopCartService.getAllProductsByOrderId(user.getOrderDto().getId()));
                break;
            case 2:
                shoppingCartView.showMessage("Пока не доступно, но скоро будет! :)");
                //TODO: Сделать паттерны оплаты
                break;
            case 3:
                level = storeMenuView.getLevel();
                break;
        }
    }

    private void signIn() {
        UserDto userDto = userService.logIn(singInView.logIn());
        if (userDto != null) {
            user = userDto;
            user.setOrderDto(orderService.getLastOrderByUserId(user.getId()));
            singInView.showMessage("Вход успешно совершён!\nДобро пожаловать, " +
                    ((user.getFio() != null && !user.getFio().equals("")) ? user.getFio() : user.getLogin()) +
                    "!");
            level = storeMenuView.getLevel();
        } else {
            singInView.showMessage("Ошибка входа! Проверьте правильность логина и пароля!");
        }
    }

    private void registerUser() {
        UserDto userDto = userService.registrationNewUser(singInView.registration());
        if (userDto != null) {
            user = userDto;
            user.setOrderDto(orderService.getLastOrderByUserId(user.getId()));
            singInView.showMessage("Пользователь успешно зарегестрирован!" + "\nДобро пожаловать, " +
                    ((user.getFio() != null && !user.getFio().equals("")) ? user.getFio() : user.getLogin()) + "!");

            level = storeMenuView.getLevel();
        } else {
            singInView.showMessage("Ошибка регистрации! Такой же логин, e-mail или телефон уже зарегестрированы в системе!");
        }
    }

    private void exitFromApp() {
        singInView.showMessage("Досвидания!");
        System.exit(0);
    }
}
