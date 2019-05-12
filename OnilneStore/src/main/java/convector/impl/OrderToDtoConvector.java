package convector.impl;

import convector.Convector;
import dto.OrderDto;
import entity.Order;

public class OrderToDtoConvector implements Convector<OrderDto, Order> {

    @Override
    public OrderDto convert(Order obj) {
        if (obj == null) return null;
        return new OrderDto(
                obj.getId(),
                obj.getUserId(),
                obj.getDate(),
                obj.getStatusId()
        );
    }
}
