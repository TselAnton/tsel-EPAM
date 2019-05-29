package convector.impl.entity_to_dto;

import convector.Convector;
import dto.OrderDto;
import generator.dto.OrderDtoGenerator;
import model.impl.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class OrderToDtoConvector implements Convector<OrderDto, Order> {

    @Autowired
    @Qualifier("orderDtoGenerator")
    private OrderDtoGenerator orderDtoGenerator;

    @Override
    public OrderDto convert(Order obj) {
        if (obj == null) return null;
        OrderDto orderDto = orderDtoGenerator.generateNewOrderDto();
        orderDto.setId(obj.getId());
        orderDto.setDate(obj.getDate());
        orderDto.setStatusId(obj.getStatusId());
        orderDto.setUserId(obj.getUserId());
        return orderDto;
    }
}
