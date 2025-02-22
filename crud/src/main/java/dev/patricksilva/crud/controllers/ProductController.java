package dev.patricksilva.crud.controllers;

import dev.patricksilva.crud.models.services.ProductServiceImpl;
import dev.patricksilva.crud.models.shared.ProductDTO;
import dev.patricksilva.crud.view.ProductRequest;
import dev.patricksilva.crud.view.ProductResponse;
import dev.patricksilva.crud.models.exception.ResourceNotFoundException;
import dev.patricksilva.crud.models.utils.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final ProductMapper MAPPER = ProductMapper.INSTANCE;

    @Autowired
    private ProductServiceImpl productService;

    /**
     * Retrieves all products in the system.
     *
     * @return a ResponseEntity containing a list of ProductResponse objects.
     */
    @Operation(summary = "Get all products", description = "Retrieve a list of all products in the system.")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductDTO> products = productService.findAll();
        List<ProductResponse> response = products.stream()
                .map(MAPPER::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves a product by its unique identifier (ID).
     *
     * @param id the ID of the product to be retrieved.
     * @return a ResponseEntity containing the ProductResponse.
     * @throws ResourceNotFoundException if the product with the given ID is not found.
     */
    @Operation(summary = "Get product by ID", description = "Retrieve a product by its unique identifier (ID).")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Integer id) {
        ProductDTO productDTO = productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));

        ProductResponse response = MAPPER.toResponse(productDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Adds a new product to the system.
     *
     * @param productRequest the product information to be added.
     * @return a ResponseEntity containing the created ProductResponse.
     */
    @Operation(summary = "Add a new product", description = "Create a new product by providing the required details.")
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
        ProductDTO productDTO = MAPPER.toDtoFromRequest(productRequest);
        productDTO = productService.addProduct(productDTO);
        ProductResponse response = MAPPER.toResponse(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Deletes a product by its unique identifier (ID).
     *
     * @param id the ID of the product to be deleted.
     * @return a ResponseEntity with no content, indicating successful deletion.
     */
    @Operation(summary = "Delete product", description = "Delete a product by its unique identifier (ID).")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates the details of an existing product.
     *
     * @param productRequest the updated product information.
     * @param id the ID of the product to be updated.
     * @return a ResponseEntity containing the updated ProductResponse.
     */
    @Operation(summary = "Update product", description = "Update the details of an existing product by its ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody ProductRequest productRequest, @PathVariable Integer id) {
        ProductDTO productDTO = MAPPER.toDtoFromRequest(productRequest);
        productDTO.setId(id);
        productDTO = productService.update(id, productDTO);
        ProductResponse response = MAPPER.toResponse(productDTO);
        return ResponseEntity.ok(response);
    }
}