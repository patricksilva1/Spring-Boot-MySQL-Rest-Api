package dev.patricksilva.crud.models.security.jwt.payload.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    /**
     * Retrieves the email from the login request.
     *
     * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email in the login request.
     *
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password from the login request.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password in the login request.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}