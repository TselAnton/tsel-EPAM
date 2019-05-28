package convector.impl.dto_to_entity;

import convector.Convector;
import dto.ProductDto;
import generator.entity.ProductGenerator;
import model.impl.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class DtoToProductConvector implements Convector<Product, ProductDto> {

    @Autowired
    @Qualifier("productGenerator")
    private ProductGenerator productGenerator;

    @Override
    public Product convert(ProductDto dto) {
        if (dto == null) return null;
        Product product = productGenerator.generateNewProduct();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setCategoryId(dto.getCategoryId());
        product.setBrand(dto.getBrand());
        product.setPrice(dto.getPrice());
        product.setQty(dto.getQty());
        product.setDiscount(dto.getDiscount());
        product.setDescription(dto.getDescription());

        return product;
    }
}
