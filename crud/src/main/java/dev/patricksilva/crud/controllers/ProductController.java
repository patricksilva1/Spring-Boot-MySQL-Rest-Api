package dev.patricksilva.crud.controllers;

import dev.patricksilva.crud.models.services.ProductService;
import dev.patricksilva.crud.models.shared.ProductDTO;
import dev.patricksilva.crud.view.ProductRequest;
import dev.patricksilva.crud.view.ProductResponse;
import dev.patricksilva.crud.models.exception.ResourceNotFoundException;
import dev.patricksilva.crud.models.utils.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    private static final ProductMapper MAPPER = ProductMapper.INSTANCE;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get all products", description = "Retrieve a list of all products in the system.")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        logger.info("Retrieving all products");
        List<ProductDTO> products = productService.findAll();
        List<ProductResponse> response = products.stream()
                .map(MAPPER::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get product by ID", description = "Retrieve a product by its unique identifier (ID).")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Integer id) {
        logger.info("Retrieving product with ID: {}", id);
        ProductDTO productDTO = productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));

        ProductResponse response = MAPPER.toResponse(productDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add a new product", description = "Create a new product by providing the required details.")
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest) {
        logger.info("Adding new product: {}", productRequest);
        ProductDTO productDTO = MAPPER.toDtoFromRequest(productRequest);
        productDTO = productService.addProduct(productDTO);
        ProductResponse response = MAPPER.toResponse(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Delete product", description = "Delete a product by its unique identifier (ID).")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        logger.info("Deleting product with ID: {}", id);
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update product", description = "Update the details of an existing product by its ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@Valid @RequestBody ProductRequest productRequest, @PathVariable Integer id) {
        logger.info("Updating product with ID: {}", id);
        ProductDTO productDTO = MAPPER.toDtoFromRequest(productRequest);
        productDTO.setId(id);
        productDTO = productService.update(id, productDTO);
        ProductResponse response = MAPPER.toResponse(productDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get products by name", description = "Retrieve products by their name.")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductResponse>> findByName(@PathVariable String name) {
        logger.info("Retrieving products with name: {}", name);
        List<ProductDTO> products = productService.findByName(name);
        List<ProductResponse> response = products.stream()
                .map(MAPPER::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update product quantity", description = "Update the quantity of an existing product by its ID.")
    @PatchMapping("/{id}/quantity")
    public ResponseEntity<ProductResponse> updateQuantity(@PathVariable Integer id, @RequestParam Integer quantity) {
        logger.info("Updating quantity of product with ID: {}", id);
        ProductDTO productDTO = productService.updateQuantity(id, quantity);
        ProductResponse response = MAPPER.toResponse(productDTO);
        return ResponseEntity.ok(response);
    }
}