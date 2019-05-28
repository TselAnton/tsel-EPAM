package configurate;

import dto.OrderDto;
import dto.ProductDto;
import dto.UserDto;
import generator.dto.OrderDtoGenerator;
import generator.dto.ProductDtoGenerator;
import generator.dto.UserDtoGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DtoGeneratorsConfiguration {

    @Bean(name = "orderDtoGenerator")
    @Scope("prototype")
    public OrderDtoGenerator orderDtoGenerator() {
        return new OrderDtoGenerator() {
            @Override
            public OrderDto generateNewOrderDto() {
                return new OrderDto();
            }
        };
    }

    @Bean(name = "productDtoGenerator")
    @Scope("prototype")
    public ProductDtoGenerator productDtoGenerator() {
        return new ProductDtoGenerator() {
            @Override
            public ProductDto generateNewProductDto() {
                return new ProductDto();
            }
        };
    }

    @Bean(name = "userDtoGenerator")
    @Scope("prototype")
    public UserDtoGenerator userDtoGenerator() {
        return new UserDtoGenerator() {
            @Override
            public UserDto generateNewUserDto() {
                return new UserDto();
            }
        };
    }
}
