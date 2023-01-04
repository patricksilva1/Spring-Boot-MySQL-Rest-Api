package dev.patricksilva.crud.view.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.patricksilva.crud.model.Product;
import dev.patricksilva.crud.services.ProductService;
import dev.patricksilva.crud.shared.ProductDTO;
import dev.patricksilva.crud.view.model.ProductResponse;

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

}
