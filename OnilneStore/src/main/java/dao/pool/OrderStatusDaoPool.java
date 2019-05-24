package dao.pool;

import model.OrderStatus;

import java.util.HashMap;

public interface OrderStatusDaoPool {

    public HashMap<Integer, OrderStatus> getAllStatuses();
}
