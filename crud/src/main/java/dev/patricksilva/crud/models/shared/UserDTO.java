package dev.patricksilva.crud.models.shared;

import dev.patricksilva.crud.models.entities.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<String> roles; // Apenas os nomes dos papéis

    // Construtor com 5 argumentos
    public UserDTO(Long id, String name, String email, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles.stream().map(Role::getName).collect(Collectors.toSet());
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}