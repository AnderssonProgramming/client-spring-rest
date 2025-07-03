package edu.eci.arsw.client_spring_rest.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for student response.
 * Used to send student information to clients.
 * 
 * @author Generated
 * @version 1.0
 */
public class StudentResponse {

    private String id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private String program;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Default constructor
    public StudentResponse() {}

    // Constructor with parameters
    public StudentResponse(String id, String name, String email, LocalDate birthDate, 
                          String program, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.program = program;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "StudentResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", program='" + program + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
