package service;

import convector.impl.DtoToProductConvector;
import convector.impl.ListProductToListDtoConvector;
import convector.impl.ProductToDtoConvector;
import dao.impl.ProductDaoImpl;
import dto.ProductDto;
import entity.Product;

import java.util.List;

public final class ProductService {

    private final ProductDaoImpl productDao = new ProductDaoImpl();
    private final ProductToDtoConvector productToDtoConvector = new ProductToDtoConvector();
    private final DtoToProductConvector dtoToProductConvector = new DtoToProductConvector();

     public List<ProductDto> getAllProducts() {
        List<Product> products = productDao.getAllProducts();
        return new ListProductToListDtoConvector().convert(products);
    }

    public ProductDto getProductById(int id) {
         return productToDtoConvector.convert(productDao.getProductById(id));
    }

    public int addProduct(ProductDto dto) {
         return productDao.addProduct(dtoToProductConvector.convert(dto));
    }

    private int addProduct(Product product) {
        return productDao.addProduct(product);
    }

    public int updateProduct(ProductDto dto) {
         return productDao.updateProduct(dtoToProductConvector.convert(dto));
    }

    private int updateProduct(Product product) {
         return productDao.updateProduct(product);
    }

    public int updateProductDiscount(ProductDto dto) {
         return productDao.updateProductDiscount(dtoToProductConvector.convert(dto));
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
