package service;

import convector.impl.dto_to_entity.DtoToProductConvector;
import convector.impl.entity_list_to_dto_list.ListProductToListDtoConvector;
import convector.impl.entity_to_dto.ProductToDtoConvector;
import dao.impl.ProductDaoImpl;
import dto.ProductDto;
import model.impl.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public final class ProductService {

    @Autowired
    private ProductDaoImpl productDao;

    @Autowired
    private ListProductToListDtoConvector listProductToListDtoConvector;

    @Autowired
    private ProductToDtoConvector productToDtoConvector;

    @Autowired
    private DtoToProductConvector dtoToProductConvector;

     public List<ProductDto> getAllProducts() {
        List<Product> products = productDao.getAllProducts();
        return listProductToListDtoConvector.convert(products);
    }

    public ProductDto getProductById(int id) {
         return productToDtoConvector.convert(productDao.getProductById(id));
    }

    private int addProduct(Product product) {
         return productDao.addProduct(product) ? 1 : 0;
    }

    private int updateProduct(Product product) {
         return productDao.updateProduct(product) ? 1 : 0;
    }

    public int updateProductDiscount(ProductDto dto) {
         return productDao.updateProductDiscount(dtoToProductConvector.convert(dto)) ? 1 : 0;
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
