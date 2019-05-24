package dao;

import model.Product;

import java.util.List;

public interface ProductDao {

    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean updateProductDiscount(Product product);

    Product getProductById(int id);
    List<Product> getAllProducts();
}
