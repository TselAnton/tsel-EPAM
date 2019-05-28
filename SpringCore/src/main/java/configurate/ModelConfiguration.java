package configurate;

import model.Model;
import model.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ModelConfiguration {

    @Bean
    @Scope("prototype")
    public Model category() {
        return new Category();
    }

    @Bean
    @Scope("prototype")
    public Model order() {
        return new Order();
    }

    @Bean
    @Scope("prototype")
    public Model orderStatus() {
        return new OrderStatus();
    }

    @Bean
    @Scope("prototype")
    public Model product() {
        return new Product();
    }

    @Bean
    @Scope("prototype")
    public Model role() {
        return new Role();
    }

    @Bean
    @Scope("prototype")
    public Model user() {
        return new User();
    }
}
