package az.company.training.mapper;

import az.company.training.dto.request.ProductCreateRequest;
import az.company.training.dto.response.ProductResponse;
import az.company.training.entity.Product;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring") // Spring bean kimi tanıması üçün
public interface ProductMapper {

    // Request-dən Entity-yə
    Product mapToEntity(ProductCreateRequest request);

    // Entity-dən Response-a
    ProductResponse mapToResponse(Product product);
}