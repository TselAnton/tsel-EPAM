package service;

import convector.impl.DtoToOrderConvector;
import convector.impl.DtoToProductConvector;
import convector.impl.ProductToDtoConvector;
import dao.impl.ShopCartDaoImpl;
import dto.OrderDto;
import dto.ProductDto;
import entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ShopCartService {

    private final ShopCartDaoImpl shopCartDao = new ShopCartDaoImpl();
    private final ProductToDtoConvector productToDtoConvector = new ProductToDtoConvector();
    private final DtoToOrderConvector dtoToOrderConvector = new DtoToOrderConvector();
    private final DtoToProductConvector dtoToProductConvector = new DtoToProductConvector();

    private Logger logger = LoggerFactory.getLogger(ShopCartService.class.getName());

    public List<ProductDto> getAllProductsByOrderId(int orderId) {
        List<Product> products = shopCartDao.getAllProductsByOrderId(orderId);
        HashMap<Integer, ProductDto> dtoHashMap = new HashMap<>();

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (!dtoHashMap.containsKey(p.getId())) {
                dtoHashMap.put(p.getId(), productToDtoConvector.convert(p));
            } else {
                dtoHashMap.get(p.getId()).sumCount(1);
            }
        }

        return new ArrayList<>(dtoHashMap.values());
    }

    public boolean addProductInShopCart(OrderDto orderDto, ProductDto productDto) {
        boolean result = shopCartDao.addToCaryProduct(dtoToOrderConvector.convert(orderDto),
                dtoToProductConvector.convert(productDto));
        return result;
    }
}
