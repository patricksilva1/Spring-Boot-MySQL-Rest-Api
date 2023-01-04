package dev.patricksilva.crud.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.patricksilva.crud.model.Product;
import dev.patricksilva.crud.repository.ProductRepository;
import dev.patricksilva.crud.shared.ProductDTO;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> findAll() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> new ModelMapper()
                        .map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> findById(Long id) {

        Optional<Product> product = productRepository.findById(Math.toIntExact(id));

        if (product.isEmpty()) {
            throw new RuntimeException("Id: " + id + " not found!");
        }

        ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);
        return Optional.of(dto);
    }
}
