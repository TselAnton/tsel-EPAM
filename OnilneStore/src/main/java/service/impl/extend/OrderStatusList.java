package service.impl.extend;

import dao.impl.OrderStatusDaoImpl;
import entity.OrderStatus;
import service.impl.AbstractListOfNames;

import java.util.HashMap;

public final class OrderStatusList extends AbstractListOfNames<Integer, OrderStatus> {

    private final OrderStatusDaoImpl orderStatusDao = new OrderStatusDaoImpl();
    private HashMap<Integer, OrderStatus> statuses;

    public OrderStatusList() {
        statuses = orderStatusDao.getAllStatuses();
        this.setHashMap(statuses);
    }
}
