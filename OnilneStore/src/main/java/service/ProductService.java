package service;

import dao.impl.ProductDaoImpl;
import dto.ProductDto;
import model.Product;
import utils.Convectors;

import java.util.List;

public final class ProductService {

    private final ProductDaoImpl productDao = new ProductDaoImpl();

     public List<ProductDto> getAllProducts() {
        List<Product> products = productDao.getAllProducts();
        return Convectors.LIST_PRODUCT_TO_LIST_DTO_CONVECTOR.convert(products);
    }

    public ProductDto getProductById(int id) {
         return Convectors.PRODUCT_TO_DTO_CONVECTOR.convert(productDao.getProductById(id));
    }

    private int addProduct(Product product) {
         return productDao.addProduct(product) ? 1 : 0;
    }

    private int updateProduct(Product product) {
         return productDao.updateProduct(product) ? 1 : 0;
    }

    public int updateProductDiscount(ProductDto dto) {
         return productDao.updateProductDiscount(Convectors.DTO_TO_PRODUCT_CONVECTOR.convert(dto)) ? 1 : 0;
    }

    public int addProductsFromFile(List<Product> products) {

         if (products == null) {
             return 0;
         }
         int count = 0;

         for (int i = 0; i < products.size(); i++) {
             Product p = products.get(i);
             int trying = addProduct(p);
             if (trying == 0) trying = updateProduct(p);
             count += trying;
         }

         return count;
    }
}
