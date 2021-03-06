package dao;

import model.Order;

import java.util.List;

public interface OrderDao {

    boolean addOrder(Order order);
    boolean updateOrder(Order order);

    Order getLastOrderByUserId(int userId);
    List<Order> getAllOrdersByUserId(int userId);
}
