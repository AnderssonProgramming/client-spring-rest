package edu.eci.arsw.client_spring_rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

/**
 * Data Transfer Object for creating a new student.
 * Contains validation annotations to ensure data integrity.
 * 
 * @author Generated
 * @version 1.0
 */
public class StudentCreateRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Program is required")
    private String program;

    // Default constructor
    public StudentCreateRequest() {}

    // Constructor with parameters
    public StudentCreateRequest(String name, String email, LocalDate birthDate, String program) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.program = program;
    }

    // Getters and Setters
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

    @Override
    public String toString() {
        return "StudentCreateRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", program='" + program + '\'' +
                '}';
    }
}
