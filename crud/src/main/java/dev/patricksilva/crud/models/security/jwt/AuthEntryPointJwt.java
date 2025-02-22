package dev.patricksilva.crud.models.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    /**
     * Handles an unauthorized request, logging the error message and sending an appropriate response.
     *
     * @param request - The HttpServletRequest object representing the incoming request.
     * @param response - The HttpServletResponse object representing the response to be sent.
     * @param authException - The AuthenticationException that occurred during the authentication process.
     * @throws IOException - if an I/O error occurs while sending the response.
     * @throws ServletException - if a servlet-related error occurs while handling the request.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}