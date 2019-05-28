package service;

import convector.impl.dto_to_entity.DtoToOrderConvector;
import convector.impl.dto_to_entity.DtoToProductConvector;
import convector.impl.entity_to_dto.ProductToDtoConvector;
import dao.pool.impl.ShopCartDaoPoolImpl;
import dto.OrderDto;
import dto.ProductDto;
import generator.collection.HashMapGenerator;
import model.impl.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public final class ShopCartService {

    @Autowired
    private  ShopCartDaoPoolImpl shopCartDao;

    @Autowired
    @Qualifier("productDtoHashMap")
    private HashMapGenerator hashMapGenerator;

    @Autowired
    private ProductToDtoConvector productToDtoConvector;

    @Autowired
    private DtoToOrderConvector dtoToOrderConvector;

    @Autowired
    private DtoToProductConvector dtoToProductConvector;

    public List<ProductDto> getAllProductsByOrderId(int orderId) {
        List<Product> products = shopCartDao.getAllProductsByOrderId(orderId);
        HashMap<Integer, ProductDto> dtoHashMap = hashMapGenerator.generateHashMap();

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (!dtoHashMap.containsKey(p.getId())) {
                dtoHashMap.put(p.getId(), productToDtoConvector.convert(p));
            } else {
                dtoHashMap.get(p.getId()).sumCount(1);
            }
        }

        return dtoHashMap.values().stream().collect(Collectors.toList());
    }

    public boolean addProductInShopCart(OrderDto orderDto, ProductDto productDto) {
        boolean result = shopCartDao.addToCaryProduct(dtoToOrderConvector.convert(orderDto),
                dtoToProductConvector.convert(productDto));
        return result;
    }
}
