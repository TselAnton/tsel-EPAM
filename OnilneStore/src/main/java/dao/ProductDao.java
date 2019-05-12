package dao;

import entity.Product;

import java.util.List;

public interface ProductDao {

    int addProduct(Product product);
    int updateProduct(Product product);
    int updateProductDiscount(Product product);

    Product getProductById(int id);

    List<Product> getAllProducts();
}
