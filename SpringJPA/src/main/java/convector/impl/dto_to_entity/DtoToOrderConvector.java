package convector.impl.dto_to_entity;

import convector.Convector;
import dto.OrderDto;
import generator.entity.OrderGenerator;
import model.impl.Order;
import org.springframework.beans.factory.annotation.Autowired;

public class DtoToOrderConvector implements Convector<Order, OrderDto> {

    @Autowired
    private OrderGenerator orderGenerator;

    @Override
    public Order convert(OrderDto obj) {
        Order order = orderGenerator.generateNewOrder();
        order.setId(obj.getId());
        order.setUserId(obj.getUserId());
        order.setUserId(obj.getUserId());
        order.setDate(obj.getDate());
        order.setStatusId(obj.getStatusId());

        return order;
    }
}
