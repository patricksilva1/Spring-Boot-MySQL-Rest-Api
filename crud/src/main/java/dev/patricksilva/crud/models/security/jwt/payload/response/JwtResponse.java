package dev.patricksilva.crud.models.security.jwt.payload.response;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
    private String token;
    private Long id;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String token, Long id, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.authorities = authorities;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}