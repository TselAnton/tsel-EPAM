package service.impl.extend;

import dao.pool.impl.OrderStatusDaoPoolImpl;
import model.impl.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import service.impl.AbstractListOfNames;

import java.util.HashMap;

public final class OrderStatusList extends AbstractListOfNames<Integer, OrderStatus> {

    @Autowired
    private OrderStatusDaoPoolImpl orderStatusDao;
    private HashMap<Integer, OrderStatus> statuses;

    public OrderStatusList() {
    }

    @Override
    public void initialize() {
        statuses = orderStatusDao.getAllStatuses();
        this.setHashMap(statuses);
    }
}
