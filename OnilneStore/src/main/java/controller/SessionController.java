package controller;

import dao.impl.ShopCartDaoImpl;
import dto.OrderDto;
import dto.ProductDto;
import dto.UserDto;
import service.OrderService;
import service.ShopCartService;
import service.impl.extend.CategoryList;
import service.ProductService;
import service.impl.extend.OrderStatusList;
import service.impl.extend.RoleList;
import service.UserService;
import view.impl.extend.*;

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


    private SessionController() {}

    public static SessionController getInstance() {
        if (init == null) {
            init = new SessionController();
        }
        return init;
    }

    public void runApp() {

        while (true) {
            if (user == null) {
                int point = singInView.showMenu();

                if (point == 1) {
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

                } else if (point == 2) {
                    UserDto userDto = userService.registrationNewUser(singInView.registration());
                    if (userDto != null) {
                        user = userDto;
                        user.setOrderDto(orderService.getLastOrderByUserId(user.getId()));
                        singInView.showMessage("Пользователь успешно зарегестрирован!" + "\nДобро пожаловать, " +
                                ((user.getFio() != null && !user.getFio().equals("")) ? user.getFio() : user.getLogin()) + "!");

                        level = storeMenuView.getLevel();
                    } else {
                        // Не стал заморачиваться по поводу проверки что именно не уникально, ибо нет времени :(
                        singInView.showMessage("Ошибка регистрации! Такой же логин, e-mail или телефон уже зарегестрированы в системе!");
                    }

                } else if (point == 3) {
                    singInView.showMessage("Досвидания!");
                    System.exit(0);
                }

            /* Работа с основным меню */
            } else if (level == storeMenuView.getLevel()) {
                roleList = new RoleList();
                orderStatusList = new OrderStatusList();

                int point = storeMenuView.showMenu();

                if (point == 1) {
                    level = catalogView.getLevel();
                    catalogView.setProducts(productService.getAllProducts());

                } else if (point == 2) {
                    level = shoppingCartView.getLevel();

                } else if (point == 3) {
                    storeMenuView.showUserProfile(user.toString(roleList.getAllNames()));

                } else if (point == 4) {
                    storeMenuView.showUserOrders(orderService.getAllOrdersByUserId(user.getId()),
                            new OrderStatusList().getAllNames());

                } else if (point == 5) {
                    if (user.getRoleId() == 1) {
                        level = adminPanelView.getLevel();
                    } else {
                        storeMenuView.showMessage("У вас нет доступа к административной панели!");
                    }

                } else if (point == 6) {
                    user = null;
                    level = singInView.getLevel();

                } else if (point == 7) {
                    storeMenuView.showMessage("Досвидания!");
                    System.exit(0);
                }

            /* Работа с каталогом товаров */
            } else if (level == catalogView.getLevel()) {
                categoryList = new CategoryList();

                int point = catalogView.showMenu();

                if (point == 1) {
                    catalogView.pageUp();

                } else if (point == 2) {
                    catalogView.pageDown();

                } else if (point == 3) {
                    int num = catalogView.getIdOfProduct();
                    if (num != 0) {
                        catalogView.showProduct(num, categoryList.getAllNames());
                    }

                } else if (point == 4) {
                    ProductDto productDto = catalogView.getProduct();
                    if (productDto != null) {
                        boolean result = shopCartService.addProductInShopCart(user.getOrderDto(), productDto);
                        if (result) {
                            catalogView.showMessage("Товера успешно добавлен в корзину!");
                        } else {
                            catalogView.showMessage("Не удалось добавить товар в корзину! Попробуйте ещё раз!");
                        }

                    }

                } else if (point == 5) {
                    catalogView.resetPage();
                    level = storeMenuView.getLevel();
                }

            /* Админ панель */
            } else if (level == adminPanelView.getLevel()) {

                int point = adminPanelView.showMenu();

                if (point == 1) {
                    /* СОЗДАНИЕ ПРОМОКОДА */
                    ProductDto dto = adminPanelView.setDiscount(productService.getAllProducts());
                    if (dto != null) {
                        int result = productService.updateProductDiscount(dto);
                        if (result != 0) {
                            adminPanelView.showMessage("Скидка успешно изменена!");
                        } else {
                            adminPanelView.showMessage("Ошибка создания скидки!");
                        }
                    }

                } else if (point == 2) {
                    /* ЗАГРУЗИТЬ ТОВАРЫ */
                    int count = productService.addProductsFromFile(adminPanelView.getProductsFromXml());
                    adminPanelView.showMessage("Было изменено записей: " + count);

                } else if (point == 3) {
                    level = storeMenuView.getLevel();
                }

            /* Корзина товаров */
            } else if (level == shoppingCartView.getLevel()) {

                int point = shoppingCartView.showMenu();

                if (point == 1) {
                    shoppingCartView.showShoppingCart(shopCartService.getAllProductsByOrderId(user.getOrderDto().getId()));

                } else if (point == 2) {
                    /* ОПЛАТА ТОВАРА */
                    shoppingCartView.showMessage("Пока не доступно, но скоро будет! :)");

                } else if (point == 3) {
                    level = storeMenuView.getLevel();
                }
            }
        }
    }
}
