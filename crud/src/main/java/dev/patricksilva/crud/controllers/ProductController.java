package dev.patricksilva.crud.controllers;

import dev.patricksilva.crud.models.services.ProductServiceImpl;
import dev.patricksilva.crud.models.shared.ProductDTO;
import dev.patricksilva.crud.view.ProductRequest;
import dev.patricksilva.crud.view.ProductResponse;
import dev.patricksilva.crud.models.exception.ResourceNotFoundException;
import dev.patricksilva.crud.models.utils.ProductMapper;
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

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductDTO> products = productService.findAll();
        List<ProductResponse> response = products.stream()
                .map(MAPPER::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Integer id) {
        ProductDTO productDTO = productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));

        ProductResponse response = MAPPER.toResponse(productDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
        ProductDTO productDTO = MAPPER.toDtoFromRequest(productRequest);
        productDTO = productService.addProduct(productDTO);
        ProductResponse response = MAPPER.toResponse(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody ProductRequest productRequest, @PathVariable Integer id) {
        ProductDTO productDTO = MAPPER.toDtoFromRequest(productRequest);
        productDTO.setId(id);
        productDTO = productService.update(id, productDTO);
        ProductResponse response = MAPPER.toResponse(productDTO);
        return ResponseEntity.ok(response);
    }
}
