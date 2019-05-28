package convector.impl.entity_to_dto;

import convector.Convector;
import dto.ProductDto;
import generator.dto.ProductDtoGenerator;
import model.impl.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductToDtoConvector implements Convector<ProductDto, Product> {

    @Autowired
    private ProductDtoGenerator productDtoGenerator;

    @Override
    public ProductDto convert(Product obj) {
        if (obj == null) return null;
        ProductDto productDto = productDtoGenerator.generateNewProductDto();

        productDto.setId(obj.getId());
        productDto.setName(obj.getName());
        productDto.setCategoryId(obj.getCategoryId());
        productDto.setBrand(obj.getBrand());
        productDto.setPrice(obj.getPrice());
        productDto.setQty(obj.getQty());
        productDto.setDiscount(obj.getDiscount());
        productDto.setDescription(obj.getDescription());

        return productDto;
    }
}
