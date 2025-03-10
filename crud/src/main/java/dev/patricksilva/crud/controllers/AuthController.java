package dev.patricksilva.crud.controllers;

import dev.patricksilva.crud.models.entities.User;
import dev.patricksilva.crud.models.security.jwt.JwtUtils;
import dev.patricksilva.crud.models.security.jwt.payload.request.LoginRequest;
import dev.patricksilva.crud.models.security.services.UserDetailsImpl;
import dev.patricksilva.crud.models.services.PeopleService;
import dev.patricksilva.crud.models.services.ProductService;
import dev.patricksilva.crud.models.shared.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ProductService productService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<ProductDTO> products = productService.findAll();
        model.addAttribute("products", products);
        return "product";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest, Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            model.addAttribute("jwt", jwt);
            model.addAttribute("user", userDetails);
            return "redirect:/products";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (peopleService.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already registered!");
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        peopleService.savePeople(user);
        return "redirect:/products";
    }
}