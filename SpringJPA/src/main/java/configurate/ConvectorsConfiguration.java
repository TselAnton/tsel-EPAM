package configurate;

import convector.impl.dto_to_entity.DtoToOrderConvector;
import convector.impl.dto_to_entity.DtoToProductConvector;
import convector.impl.dto_to_entity.DtoToUserConvector;
import convector.impl.entity_list_to_dto_list.ListProductToListDtoConvector;
import convector.impl.entity_to_dto.OrderToDtoConvector;
import convector.impl.entity_to_dto.ProductToDtoConvector;
import convector.impl.entity_to_dto.UserToDtoConvector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConvectorsConfiguration {

    @Bean
    public DtoToUserConvector dtoToUserConvector() {
        return new DtoToUserConvector();
    }

    @Bean
    public DtoToProductConvector dtoToProductConvector() {
        return new DtoToProductConvector();
    }

    @Bean
    public DtoToOrderConvector dtoToOrderConvector() {
        return new DtoToOrderConvector();
    }

    @Bean
    public OrderToDtoConvector orderToDtoConvector() {
        return new OrderToDtoConvector();
    }

    @Bean
    public ProductToDtoConvector productToDtoConvector() {
        return new ProductToDtoConvector();
    }

    @Bean
    public UserToDtoConvector userToDtoConvector() {
        return new UserToDtoConvector();
    }

    @Bean
    public ListProductToListDtoConvector listProductToListDtoConvector() {
        return new ListProductToListDtoConvector();
    }
}
