package dev.patricksilva.crud.models.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.patricksilva.crud.models.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.patricksilva.crud.models.entities.Product;
import dev.patricksilva.crud.models.exception.ResourceNotFoundException;
import dev.patricksilva.crud.models.repository.ProductRepository;
import dev.patricksilva.crud.models.shared.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final Executor executor = Executors.newVirtualThreadPerTaskExecutor();

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(ProductMapper.INSTANCE::toDto)
                .toList();
    }

    public Optional<ProductDTO> findById(int id) {
        return productRepository.findById(id)
                .map(ProductMapper.INSTANCE::toDto)
                .or(() -> {
                    throw new ResourceNotFoundException("Id: " + id + " not found!");
                });
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        product = productRepository.save(product);
        return ProductMapper.INSTANCE.toDto(product);
    }

    public void delete(int id) {
        CompletableFuture.runAsync(() -> {
            if (!productRepository.existsById(id)) {
                throw new ResourceNotFoundException("Could not delete product with id: " + id + ", does not exist!");
            }
            productRepository.deleteById(id);
        }, executor).join();
    }

    public ProductDTO update(int id, ProductDTO productDTO) {
        productDTO.setId(id);
        return CompletableFuture.supplyAsync(() -> {
            Optional<Product> existingProduct = productRepository.findById(id);
            if (existingProduct.isEmpty()) {
                throw new ResourceNotFoundException("Product with id: " + id + " not found!");
            }
            Product product = ProductMapper.INSTANCE.toEntity(productDTO);
            product = productRepository.save(product);
            return ProductMapper.INSTANCE.toDto(product);
        }, executor).join();
    }
}
