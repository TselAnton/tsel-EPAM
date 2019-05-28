package configurate;

import dto.OrderDto;
import dto.ProductDto;
import dto.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DtoConfiguration {

    @Bean
    @Scope("prototype")
    public OrderDto orderDto() {
        return new OrderDto();
    }

    @Bean
    @Scope("prototype")
    public ProductDto productDto() {
        return new ProductDto();
    }

    @Bean
    @Scope("prototype")
    public UserDto userDto() {
        return new UserDto();
    }
}
