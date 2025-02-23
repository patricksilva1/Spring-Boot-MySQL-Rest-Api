package dev.patricksilva.crud.models.services;

import java.util.List;
import java.util.Optional;

import dev.patricksilva.crud.models.shared.ProductDTO;

public interface ProductService {
    List<ProductDTO> findAll();

    Optional<ProductDTO> findById(int id);

    ProductDTO addProduct(ProductDTO productDTO);

    void delete(int id);

    ProductDTO update(int id, ProductDTO productDTO);

    List<ProductDTO> findByName(String name);

    ProductDTO updateQuantity(int id, int quantity);
}