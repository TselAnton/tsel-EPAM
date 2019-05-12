package service;

import convector.impl.DtoToOrderConvector;
import convector.impl.OrderToDtoConvector;
import dao.impl.OrderDaoImpl;
import dto.OrderDto;
import entity.Order;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class OrderService {

    private final OrderDaoImpl orderDao = new OrderDaoImpl();
    private final OrderToDtoConvector orderToDtoConvector = new OrderToDtoConvector();
    private final DtoToOrderConvector dtoToOrderConvector = new DtoToOrderConvector();


    public OrderDto getLastOrderByUserId(int userId) {

        OrderDto orderDto = orderToDtoConvector.convert(orderDao.getLastOrderByUserId(userId));
        if (orderDto == null) {
            OrderDto newOrder = new OrderDto();
            newOrder.setUserId(userId);
            newOrder.setDate(Date.valueOf(LocalDate.now()));
            newOrder.setStatusId(1);

            if (addOrder(newOrder)) {
                return orderToDtoConvector.convert(orderDao.getLastOrderByUserId(userId));
            }
        }

        return orderDto;
    }

    public List<OrderDto> getAllOrdersByUserId(int userId) {
        List<Order> orders = orderDao.getAllOrdersByUserId(userId);
        List<OrderDto> orderDtos = new ArrayList<>();

        for (Order p : orders) {
            orderDtos.add(orderToDtoConvector.convert(p));
        }

        return orderDtos;
    }

    public boolean addOrder(OrderDto dto) {
        return orderDao.addOrder(dtoToOrderConvector.convert(dto));
    }

    public boolean updateOrder(OrderDto dto) {
        return orderDao.updateOrder(dtoToOrderConvector.convert(dto));
    }
}
