package service.impl.extend;

import dao.pool.impl.OrderStatusDaoPoolImpl;
import model.OrderStatus;
import service.impl.AbstractListOfNames;

import java.util.HashMap;

public final class OrderStatusList extends AbstractListOfNames<Integer, OrderStatus> {

    private final OrderStatusDaoPoolImpl orderStatusDao = new OrderStatusDaoPoolImpl();
    private HashMap<Integer, OrderStatus> statuses;

    public OrderStatusList() {
        statuses = orderStatusDao.getAllStatuses();
        this.setHashMap(statuses);
    }
}
