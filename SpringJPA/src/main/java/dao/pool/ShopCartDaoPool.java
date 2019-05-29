package dao.pool;

import model.impl.Order;
import model.impl.Product;

import java.util.List;

public interface ShopCartDaoPool {

    boolean addToCaryProduct(Order order, Product product);

    List<Product> getAllProductsByOrderId(int orderId);
}
