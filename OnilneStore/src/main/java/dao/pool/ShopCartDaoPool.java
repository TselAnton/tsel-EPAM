package dao.pool;

import model.Order;
import model.Product;

import java.util.List;

public interface ShopCartDaoPool {

    boolean addToCaryProduct(Order order, Product product);

    List<Product> getAllProductsByOrderId(int orderId);
}
