package dev.patricksilva.crud.controllers;

import dev.patricksilva.crud.models.shared.ProductDTO;
import dev.patricksilva.crud.models.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<ProductDTO> products = productService.findAll();
        model.addAttribute("products", products);
        return "home";
    }
}