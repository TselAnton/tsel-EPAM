package convector.impl.dto_to_entity;

import convector.Convector;
import dto.ProductDto;
import model.Product;

public class DtoToProductConvector implements Convector<Product, ProductDto> {
    @Override
    public Product convert(ProductDto dto) {
        if (dto == null) return null;
        return new Product(
                dto.getId(),
                dto.getName(),
                dto.getCategoryId(),
                dto.getBrand(),
                dto.getPrice(),
                dto.getQty(),
                dto.getDiscount(),
                dto.getDescription()
        );
    }
}
