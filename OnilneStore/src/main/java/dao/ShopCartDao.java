package dao;

import entity.Order;
import entity.Product;

import java.util.List;

public interface ShopCartDao {

    boolean addToCaryProduct(Order order, Product product);

    List<Product> getAllProductsByOrderId(int orderId);
}
