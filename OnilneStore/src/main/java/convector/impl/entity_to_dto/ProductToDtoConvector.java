package convector.impl.entity_to_dto;

import convector.Convector;
import dto.ProductDto;
import model.Product;

public class ProductToDtoConvector implements Convector<ProductDto, Product> {
    @Override
    public ProductDto convert(Product obj) {
        if (obj == null) return null;
        return new ProductDto(
                obj.getId(),
                obj.getName(),
                obj.getCategoryId(),
                obj.getBrand(),
                obj.getPrice(),
                obj.getQty(),
                obj.getDiscount(),
                obj.getDescription()
        );
    }
}
