package service;

import dao.pool.impl.ShopCartDaoPoolImpl;
import dto.OrderDto;
import dto.ProductDto;
import model.Product;
import utils.Convectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ShopCartService {

    private final ShopCartDaoPoolImpl shopCartDao = new ShopCartDaoPoolImpl();


    public List<ProductDto> getAllProductsByOrderId(int orderId) {
        List<Product> products = shopCartDao.getAllProductsByOrderId(orderId);
        HashMap<Integer, ProductDto> dtoHashMap = new HashMap<>();

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (!dtoHashMap.containsKey(p.getId())) {
                dtoHashMap.put(p.getId(), Convectors.PRODUCT_TO_DTO_CONVECTOR.convert(p));
            } else {
                dtoHashMap.get(p.getId()).sumCount(1);
            }
        }

        return new ArrayList<>(dtoHashMap.values());
    }

    public boolean addProductInShopCart(OrderDto orderDto, ProductDto productDto) {
        boolean result = shopCartDao.addToCaryProduct(Convectors.DTO_TO_ORDER_CONVECTOR.convert(orderDto),
                Convectors.DTO_TO_PRODUCT_CONVECTOR.convert(productDto));
        return result;
    }
}
