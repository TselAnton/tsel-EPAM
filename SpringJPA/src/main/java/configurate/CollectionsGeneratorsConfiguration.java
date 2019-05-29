package configurate;

import dto.OrderDto;
import dto.ProductDto;
import generator.collection.ArraysGenerator;
import generator.collection.HashMapGenerator;
import generator.utils.StringMassGenerator;
import model.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.HashMap;

@Configuration
public class CollectionsGeneratorsConfiguration {

    @Bean(name = "orderArrayList")
    @Scope("prototype")
    public ArraysGenerator generateArrayList() {
        return new ArraysGenerator() {
            @Override
            public ArrayList<Order> generateArrayList() {
                return new ArrayList<Order>();
            }
        };
    }

    @Bean(name = "orderDtoArrayList")
    @Scope("prototype")
    public ArraysGenerator orderDtoArrayList() {
        return new ArraysGenerator() {
            @Override
            public ArrayList<OrderDto> generateArrayList() {
                return new ArrayList<OrderDto>();
            }
        };
    }

    @Bean(name = "productArrayList")
    @Scope("prototype")
    public ArraysGenerator productArrayList() {
        return new ArraysGenerator() {
            @Override
            public ArrayList<Product> generateArrayList() {
                return new ArrayList<Product>();
            }
        };
    }

    @Bean(name = "productDtoArrayList")
    @Scope("prototype")
    public ArraysGenerator productDtoArrayList() {
        return new ArraysGenerator() {
            @Override
            public ArrayList<ProductDto> generateArrayList() {
                return new ArrayList<ProductDto>();
            }
        };
    }

    @Bean(name = "categoryHashMap")
    @Scope("prototype")
    public HashMapGenerator categoryHashMap() {
        return new HashMapGenerator() {
            @Override
            public HashMap<Integer, Category> generateHashMap() {
                return new HashMap<Integer, Category>();
            }
        };
    }

    @Bean(name = "orderStatusHashMap")
    @Scope("prototype")
    public HashMapGenerator orderStatusHashMap() {
        return new HashMapGenerator() {
            @Override
            public HashMap<Integer, OrderStatus> generateHashMap() {
                return new HashMap<Integer, OrderStatus>();
            }
        };
    }

    @Bean(name = "roleHashMap")
    @Scope("prototype")
    public HashMapGenerator roleHashMap() {
        return new HashMapGenerator() {
            @Override
            public HashMap<Integer, Role> generateHashMap() {
                return new HashMap<Integer, Role>();
            }
        };
    }

    @Bean(name = "productDtoHashMap")
    @Scope("prototype")
    public HashMapGenerator productDtoHashMap() {
        return new HashMapGenerator() {
            @Override
            public HashMap<Integer, ProductDto> generateHashMap() {
                return new HashMap<Integer, ProductDto>();
            }
        };
    }

    @Bean(name = "stringMassGenerator")
    @Scope("prototype")
    public StringMassGenerator stringMassGenerator() {
       return new StringMassGenerator() {
           @Override
           public String[] generateStringMass(String... strings) {
               return strings;
           }
       };
    }
}
