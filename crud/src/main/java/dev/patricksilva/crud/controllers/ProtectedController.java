package dev.patricksilva.crud.controllers;

import dev.patricksilva.crud.models.exception.ResourceNotFoundException;
import dev.patricksilva.crud.models.services.ProductService;
import dev.patricksilva.crud.models.shared.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProtectedController {

    @Autowired
    private ProductService  productService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/protected")
    public String getProtectedPage(Model model) {
        model.addAttribute("message", "You have accessed a protected page!");
        return "protected";
    }

    @GetMapping("/product-details/{id}")
    public String getProductDetails(@PathVariable Integer id, Model model) {
        ProductDTO productDTO = productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));
        model.addAttribute("product", productDTO);
        return "product-details";
    }
}