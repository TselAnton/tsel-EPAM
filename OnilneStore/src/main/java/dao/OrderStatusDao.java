package dao;

import entity.OrderStatus;

import java.util.HashMap;

public interface OrderStatusDao {

    public HashMap<Integer, OrderStatus> getAllStatuses();
}
