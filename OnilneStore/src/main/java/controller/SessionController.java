package controller;

import dto.ProductDto;
import dto.UserDto;
import service.OrderService;
import service.ProductService;
import service.ShopCartService;
import service.UserService;
import service.impl.extend.CategoryList;
import service.impl.extend.OrderStatusList;
import service.impl.extend.RoleList;
import view.impl.extend.AdminPanelView;
import view.impl.extend.CatalogView;
import view.impl.extend.ShoppingCartView;
import view.impl.extend.StoreMenuView;
import view.impl.extend.SingInView;

import java.sql.Connection;

/**
 * Класс для управление текущей сессией пользователя
 */
public final class SessionController {

    private static SessionController init = null;

    /* View */
    private SingInView singInView = new SingInView(0);
    private StoreMenuView storeMenuView = new StoreMenuView(1);
    private CatalogView catalogView = new CatalogView(2);
    private AdminPanelView adminPanelView = new AdminPanelView(3);
    private ShoppingCartView shoppingCartView = new ShoppingCartView(4);

    /* Service */
    private UserService userService = new UserService();
    private RoleList roleList = new RoleList();
    private ProductService productService = new ProductService();
    private CategoryList categoryList = new CategoryList();
    private OrderService orderService = new OrderService();
    private ShopCartService shopCartService = new ShopCartService();
    private OrderStatusList orderStatusList = new OrderStatusList();

    private Connection connection = ConnectionController.getInstance().getConnection();
    private UserDto user = null;
    private int level = 0;


    private SessionController() {
    }

    public static SessionController getInstance() {
        if (init == null) {
            init = new SessionController();
        }
        return init;
    }

    public void runApp() {
        while (true) {
            if (user == null) {
                singInViewShowMenu();

            } else if (level == storeMenuView.getLevel()) {
                roleList = new RoleList();
                orderStatusList = new OrderStatusList();
                storeMenuViewShowMenu();

            } else if (level == catalogView.getLevel()) {
                categoryList = new CategoryList();
                catalogViewShowMenu();

            } else if (level == adminPanelView.getLevel()) {
                adminPanelViewShowMenu();

            } else if (level == shoppingCartView.getLevel()) {
                shopingCartViewShowMenu();
            }
        }
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
                        new OrderStatusList().getAllNames());
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
            if (connection != null) {
                singInView.showMessage("Ошибка входа! Проверьте правильность логина и пароля!");
            } else {
                singInView.showMessage("Ошибка входа! Проверьте подключение к интернету и попробуйте ещё раз!");
            }
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
