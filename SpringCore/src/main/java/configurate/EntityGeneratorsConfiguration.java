package configurate;

import generator.entity.*;
import generator.utils.BufferReaderGenerator;
import generator.utils.ScannerGenerator;
import model.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

@Configuration
public class EntityGeneratorsConfiguration {

    @Bean
    public CategoryGenerator categoryGenerator() {
        return new CategoryGenerator() {
            @Override
            public Category generateNewCategory() {
                return new Category();
            }
        };
    }

    @Bean
    public OrderGenerator orderGenerator() {
        return new OrderGenerator() {
            @Override
            public Order generateNewOrder() {
                return new Order();
            }
        };
    }

    @Bean
    public OrderStatusGenerator orderStatusGenerator() {
        return new OrderStatusGenerator() {
            @Override
            public OrderStatus generateNewOrderStatus() {
                return new OrderStatus();
            }
        };
    }

    @Bean
    public ProductGenerator productGenerator() {
        return new ProductGenerator() {
            @Override
            public Product generateNewProduct() {
                return new Product();
            }
        };
    }

    @Bean
    public RoleGenerator roleGenerator() {
        return new RoleGenerator() {
            @Override
            public Role generateNewRole() {
                return new Role();
            }
        };
    }

    @Bean
    public UserGenerator userGenerator() {
        return new UserGenerator() {
            @Override
            public User generateNewUser() {
                return new User();
            }
        };
    }

    @Bean
    public ScannerGenerator scannerGenerator() {
        return new ScannerGenerator() {
            @Override
            public Scanner generateScanner() {
                return new Scanner(System.in);
            }
        };
    }

    @Bean
    public BufferReaderGenerator bufferReaderGenerator() {
        return new BufferReaderGenerator() {
            @Override
            public BufferedReader generateBufferReader() {
                return new BufferedReader(new InputStreamReader(System.in));
            }
        };
    }
}
