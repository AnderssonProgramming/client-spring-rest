package edu.eci.arsw.client_spring_rest.dto;

import java.time.LocalDateTime;

/**
 * Generic API response wrapper for consistent response format.
 * 
 * @param <T> the type of data being returned
 * @author Generated
 * @version 1.0
 */
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    // Default constructor
    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    // Constructor for successful response with data
    public ApiResponse(T data) {
        this();
        this.success = true;
        this.data = data;
        this.message = "Operation completed successfully";
    }

    // Constructor for successful response with custom message
    public ApiResponse(T data, String message) {
        this();
        this.success = true;
        this.data = data;
        this.message = message;
    }

    // Constructor for error response
    public ApiResponse(String errorMessage) {
        this();
        this.success = false;
        this.message = errorMessage;
        this.data = null;
    }

    // Static factory methods for common responses
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data);
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(message);
    }

    public static <T> ApiResponse<T> noContent() {
        return new ApiResponse<>(null, "No content");
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
