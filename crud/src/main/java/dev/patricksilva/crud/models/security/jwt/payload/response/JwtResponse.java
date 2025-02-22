package dev.patricksilva.crud.models.security.jwt.payload.response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String email;
    private List<String> roles;

    /**
     * Constructs a JwtResponse object with the provided access token, user ID, email, and roles.
     *
     * @param accessToken - The access token.
     * @param id - The user ID.
     * @param email - The user's email.
     * @param roles - The roles associated with the user.
     */
    public JwtResponse(String accessToken, String id, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    /**
     * Retrieves the access token from the JWT response.
     *
     * @return The access token.
     */
    public String getAccessToken() {
        return token;
    }

    /**
     * Sets the access token in the JWT response.
     *
     * @param accessToken The access token to set.
     */
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    /**
     * Retrieves the token type from the JWT response.
     *
     * @return The token type.
     */
    public String getTokenType() {
        return type;
    }

    /**
     * Sets the token type in the JWT response.
     *
     * @param tokenType The token type to set.
     */
    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    /**
     * Retrieves the user ID from the JWT response.
     *
     * @return The user ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the user ID in the JWT response.
     *
     * @param id The user ID to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieves the email from the JWT response.
     *
     * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email in the JWT response.
     *
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the roles from the JWT response.
     *
     * @return The roles.
     */
    public List<String> getRoles() {
        return roles;
    }
}