package configurate;

import controller.ConnectionController;
import dao.impl.OrderDaoImpl;
import dao.impl.ProductDaoImpl;
import dao.impl.UserDaoImpl;
import dao.pool.impl.CategoryDaoPoolImpl;
import dao.pool.impl.OrderStatusDaoPoolImpl;
import dao.pool.impl.RoleDaoPoolImpl;
import dao.pool.impl.ShopCartDaoPoolImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfiguration {

    @Autowired
    private ConnectionController connectionController;

    @Bean
    public OrderDaoImpl orderDao() {
        return new OrderDaoImpl();
    }

    @Bean
    public ProductDaoImpl productDao() {
        return new ProductDaoImpl(connectionController);
    }

    @Bean
    public UserDaoImpl userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public CategoryDaoPoolImpl categoryDaoPool() {
        return new CategoryDaoPoolImpl(connectionController);
    }

    @Bean
    public OrderStatusDaoPoolImpl orderStatusDaoPool() {
        return new OrderStatusDaoPoolImpl(connectionController);
    }

    @Bean
    public RoleDaoPoolImpl roleDaoPool() {
        return new RoleDaoPoolImpl(connectionController);
    }

    @Bean
    public ShopCartDaoPoolImpl shopCartDaoPool() {
        return new ShopCartDaoPoolImpl(connectionController);
    }
}
