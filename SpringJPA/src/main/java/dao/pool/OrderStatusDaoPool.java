package dao.pool;

import model.impl.OrderStatus;

import java.util.HashMap;

public interface OrderStatusDaoPool {

    public HashMap<Integer, OrderStatus> getAllStatuses();
}
