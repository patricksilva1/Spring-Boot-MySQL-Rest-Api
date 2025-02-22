package dev.patricksilva.crud.models.security.jwt.payload.response;

public class MessageResponse {
    private String message;

    /**
     * Constructs a MessageResponse object with the provided message.
     *
     * @param message The message.
     */
    public MessageResponse(String message) {
        this.message = message;
    }

    /**
     * Retrieves the message from the response.
     *
     * @return The message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message in the response.
     *
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}