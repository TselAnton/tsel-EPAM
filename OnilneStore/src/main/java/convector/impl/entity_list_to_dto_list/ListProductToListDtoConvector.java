package convector.impl.entity_list_to_dto_list;

import convector.Convector;
import dto.ProductDto;
import model.Product;
import utils.Convectors;

import java.util.ArrayList;
import java.util.List;

public class ListProductToListDtoConvector implements Convector<List<ProductDto>, List<Product>> {
    @Override
    public List<ProductDto> convert(List<Product> obj) {
        List<ProductDto> products = new ArrayList<>();

        for (Product p : obj) {
            products.add(Convectors.PRODUCT_TO_DTO_CONVECTOR.convert(p));
        }
        return products;
    }
}
