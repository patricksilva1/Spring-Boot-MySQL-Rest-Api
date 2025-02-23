package dev.patricksilva.crud.controllers;

import dev.patricksilva.crud.models.entities.User;
import dev.patricksilva.crud.models.security.jwt.JwtUtils;
import dev.patricksilva.crud.models.security.jwt.payload.request.LoginRequest;
import dev.patricksilva.crud.models.security.jwt.payload.response.JwtResponse;
import dev.patricksilva.crud.models.security.services.UserDetailsImpl;
import dev.patricksilva.crud.models.repository.PeopleRepository;
import dev.patricksilva.crud.models.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (peopleService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email já cadastrado!");
        }
        // Remova a linha abaixo (não codifique aqui)
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        peopleService.savePeople(user);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }
}