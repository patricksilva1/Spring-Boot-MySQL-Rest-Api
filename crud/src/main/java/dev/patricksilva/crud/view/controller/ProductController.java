package dev.patricksilva.crud.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.patricksilva.crud.services.ProductService;
import dev.patricksilva.crud.shared.ProductDTO;
import dev.patricksilva.crud.view.model.ProductRequest;
import dev.patricksilva.crud.view.model.ProductResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {

        List<ProductDTO> products = productService.findAll();

        ModelMapper mapper = new ModelMapper();

        List<ProductResponse> response = products.stream()
                .map(ProductDTO -> mapper.map(ProductDTO, ProductResponse.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> findById(@PathVariable Integer id) {

        Optional<ProductDTO> dto = productService.findById(id);

        ProductResponse product = new ModelMapper().map(dto.get(), ProductResponse.class);

        return new ResponseEntity<>(Optional.of(product), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {

        ModelMapper mapper = new ModelMapper();

        ProductDTO productDTO = mapper.map(productRequest, ProductDTO.class);

        productDTO = productService.addProduct(productDTO);

        return new ResponseEntity<>(mapper.map(productDTO, ProductResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        productService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody ProductRequest productRequest,
            @PathVariable Integer id) {
        ModelMapper mapper = new ModelMapper();

        ProductDTO productDTO = mapper.map(productRequest, ProductDTO.class);

        productDTO = productService.update(id, productDTO);

        return new ResponseEntity<>(mapper.map(productDTO, ProductResponse.class), HttpStatus.OK);
    }
}
