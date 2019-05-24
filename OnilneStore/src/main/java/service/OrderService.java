package service;

import dao.impl.OrderDaoImpl;
import dto.OrderDto;
import model.Order;
import utils.Convectors;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class OrderService {

    private final OrderDaoImpl orderDao = new OrderDaoImpl();


    public OrderDto getLastOrderByUserId(int userId) {

        OrderDto orderDto = Convectors.ORDER_TO_DTO_CONVECTOR.convert(orderDao.getLastOrderByUserId(userId));
        if (orderDto == null) {
            OrderDto newOrder = new OrderDto();
            newOrder.setUserId(userId);
            newOrder.setDate(Date.valueOf(LocalDate.now()));
            newOrder.setStatusId(1);

            if (addOrder(newOrder)) {
                return Convectors.ORDER_TO_DTO_CONVECTOR.convert(orderDao.getLastOrderByUserId(userId));
            }
        }

        return orderDto;
    }

    public List<OrderDto> getAllOrdersByUserId(int userId) {
        List<Order> orders = orderDao.getAllOrdersByUserId(userId);
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (Order p : orders) {
            orderDtoList.add(Convectors.ORDER_TO_DTO_CONVECTOR.convert(p));
        }

        return orderDtoList;
    }

    public boolean addOrder(OrderDto dto) {
        return orderDao.addOrder(Convectors.DTO_TO_ORDER_CONVECTOR.convert(dto));
    }

    public boolean updateOrder(OrderDto dto) {
        return orderDao.updateOrder(Convectors.DTO_TO_ORDER_CONVECTOR.convert(dto));
    }
}
