package configurate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import service.OrderService;
import service.ProductService;
import service.ShopCartService;
import service.UserService;
import service.impl.extend.CategoryList;
import service.impl.extend.OrderStatusList;
import service.impl.extend.RoleList;

@Configuration
public class ServiceConfiguration {

    @Bean
    public OrderService orderService() {
        return new OrderService();
    }

    @Bean
    public ProductService productService() {
        return new ProductService();
    }

    @Bean
    public ShopCartService shopCartService() {
        return new ShopCartService();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    @Scope("prototype")
    public CategoryList categoryList() {
        return new CategoryList();
    }

    @Bean
    @Scope("prototype")
    public OrderStatusList orderStatusList() {
        return new OrderStatusList();
    }

    @Bean
    @Scope("prototype")
    public RoleList roleList() {
        return new RoleList();
    }
}
