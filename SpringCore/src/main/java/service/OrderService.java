package service;

import convector.impl.dto_to_entity.DtoToOrderConvector;
import convector.impl.entity_to_dto.OrderToDtoConvector;
import dao.impl.OrderDaoImpl;
import dto.OrderDto;
import generator.collection.ArraysGenerator;
import generator.dto.OrderDtoGenerator;
import model.impl.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public final class OrderService {

    @Autowired
    private OrderDaoImpl orderDao;

    @Autowired
    private OrderDtoGenerator orderDtoGenerator;

    @Autowired
    @Qualifier("orderDtoArrayList")
    private ArraysGenerator arraysGenerator;

    @Autowired
    private OrderToDtoConvector orderToDtoConvector;

    @Autowired
    private DtoToOrderConvector dtoToOrderConvector;

    public OrderDto getLastOrderByUserId(int userId) {

        OrderDto orderDto = orderToDtoConvector.convert(orderDao.getLastOrderByUserId(userId));
        if (orderDto == null) {
            OrderDto newOrder = orderDtoGenerator.generateNewOrderDto();
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
        List<OrderDto> orderDtoList = arraysGenerator.generateArrayList();

        for (Order p : orders) {
            orderDtoList.add(orderToDtoConvector.convert(p));
        }

        return orderDtoList;
    }

    public boolean addOrder(OrderDto dto) {
        return orderDao.addOrder(dtoToOrderConvector.convert(dto));
    }

    public boolean updateOrder(OrderDto dto) {
        return orderDao.updateOrder(dtoToOrderConvector.convert(dto));
    }
}
