package convector.impl;

import convector.Convector;
import dto.ProductDto;
import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ListProductToListDtoConvector implements Convector<List<ProductDto>, List<Product>> {
    @Override
    public List<ProductDto> convert(List<Product> obj) {
        List<ProductDto> products = new ArrayList<>();

        for (Product p : obj) {
            products.add(new ProductToDtoConvector().convert(p));
        }
        return products;
    }
}
