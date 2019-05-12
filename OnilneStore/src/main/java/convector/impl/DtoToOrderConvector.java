package convector.impl;

import convector.Convector;
import dto.OrderDto;
import entity.Order;

public class DtoToOrderConvector implements Convector<Order, OrderDto> {

    @Override
    public Order convert(OrderDto obj) {
        Order order = new Order(
                obj.getId(),
                obj.getUserId(),
                obj.getDate(),
                obj.getStatusId()
        );

        return order;
    }
}
