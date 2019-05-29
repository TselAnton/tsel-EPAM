package convector.impl.entity_list_to_dto_list;

import convector.Convector;
import convector.impl.entity_to_dto.ProductToDtoConvector;
import dto.ProductDto;
import generator.collection.ArraysGenerator;
import model.impl.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class ListProductToListDtoConvector implements Convector<List<ProductDto>, List<Product>> {

    @Autowired
    @Qualifier("productDtoArrayList")
    private ArraysGenerator arraysGenerator;

    @Autowired
    private ProductToDtoConvector productToDtoConvector;

    @Override
    public List<ProductDto> convert(List<Product> obj) {
        List<ProductDto> products = arraysGenerator.generateArrayList();

        for (Product p : obj) {
            products.add(productToDtoConvector.convert(p));
        }
        return products;
    }
}
