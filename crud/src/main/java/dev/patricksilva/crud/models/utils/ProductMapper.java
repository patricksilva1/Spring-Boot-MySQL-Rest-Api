package dev.patricksilva.crud.models.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import dev.patricksilva.crud.models.entities.Product;
import dev.patricksilva.crud.models.shared.ProductDTO;
import dev.patricksilva.crud.view.ProductRequest;
import dev.patricksilva.crud.view.ProductResponse;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", source = "id")
    ProductDTO toDto(Product product);

    @Mapping(target = "id", source = "id")
    Product toEntity(ProductDTO productDTO);

    ProductResponse toResponse(ProductDTO productDTO);

    @Mapping(target = "id", ignore = true)
    ProductDTO toDtoFromRequest(ProductRequest productRequest);
}
