package utils;

import convector.impl.dto_to_entity.DtoToOrderConvector;
import convector.impl.dto_to_entity.DtoToProductConvector;
import convector.impl.dto_to_entity.DtoToUserConvector;
import convector.impl.entity_list_to_dto_list.ListProductToListDtoConvector;
import convector.impl.entity_to_dto.OrderToDtoConvector;
import convector.impl.entity_to_dto.ProductToDtoConvector;
import convector.impl.entity_to_dto.UserToDtoConvector;

public class Convectors {
    public static final DtoToOrderConvector DTO_TO_ORDER_CONVECTOR = new DtoToOrderConvector();
    public static final DtoToProductConvector DTO_TO_PRODUCT_CONVECTOR = new DtoToProductConvector();
    public static final DtoToUserConvector DTO_TO_USER_CONVECTOR = new DtoToUserConvector();

    public static final OrderToDtoConvector ORDER_TO_DTO_CONVECTOR = new OrderToDtoConvector();
    public static final ProductToDtoConvector PRODUCT_TO_DTO_CONVECTOR = new ProductToDtoConvector();
    public static final UserToDtoConvector USER_TO_DTO_CONVECTOR = new UserToDtoConvector();

    public static final ListProductToListDtoConvector LIST_PRODUCT_TO_LIST_DTO_CONVECTOR = new ListProductToListDtoConvector();
}
