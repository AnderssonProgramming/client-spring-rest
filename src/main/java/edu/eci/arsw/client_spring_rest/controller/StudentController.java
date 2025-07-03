package edu.eci.arsw.client_spring_rest.controller;

import edu.eci.arsw.client_spring_rest.dto.ApiResponse;
import edu.eci.arsw.client_spring_rest.dto.StudentCreateRequest;
import edu.eci.arsw.client_spring_rest.dto.StudentResponse;
import edu.eci.arsw.client_spring_rest.service.StudentService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

/**
 * REST Controller for Student operations.
 * Provides endpoints for CRUD operations and student queries.
 * 
 * @author Generated
 * @version 1.0
 */
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "${cors.allowed.origins:http://localhost:3000}")
@Validated
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Create a new student.
     * 
     * @param request the student creation request
     * @return the created student wrapped in ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> createStudent(
            @Valid @RequestBody StudentCreateRequest request) {
        
        StudentResponse student = studentService.createStudent(request);
        ApiResponse<StudentResponse> response = ApiResponse.success(student, "Student created successfully");
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Get all students.
     * 
     * @return list of all students wrapped in ApiResponse
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAllStudents() {
        List<StudentResponse> students = studentService.getAllStudents();
        ApiResponse<List<StudentResponse>> response = ApiResponse.success(students, 
            "Retrieved " + students.size() + " students");
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get student by ID.
     * 
     * @param id the student ID
     * @return the student wrapped in ApiResponse
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentById(@PathVariable String id) {
        StudentResponse student = studentService.getStudentById(id);
        ApiResponse<StudentResponse> response = ApiResponse.success(student, "Student found");
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get student by email.
     * 
     * @param email the student email
     * @return the student wrapped in ApiResponse
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentByEmail(
            @PathVariable @Email(message = "Invalid email format") String email) {
        
        StudentResponse student = studentService.getStudentByEmail(email);
        ApiResponse<StudentResponse> response = ApiResponse.success(student, "Student found");
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get students by program.
     * 
     * @param program the program name
     * @return list of students in the program wrapped in ApiResponse
     */
    @GetMapping("/program/{program}")
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getStudentsByProgram(
            @PathVariable @NotBlank(message = "Program cannot be blank") String program) {
        
        List<StudentResponse> students = studentService.getStudentsByProgram(program);
        ApiResponse<List<StudentResponse>> response = ApiResponse.success(students, 
            "Found " + students.size() + " students in program: " + program);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Search students by name.
     * 
     * @param name the name or partial name to search for
     * @return list of matching students wrapped in ApiResponse
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StudentResponse>>> searchStudentsByName(
            @RequestParam @NotBlank(message = "Name parameter cannot be blank") String name) {
        
        List<StudentResponse> students = studentService.searchStudentsByName(name);
        ApiResponse<List<StudentResponse>> response = ApiResponse.success(students, 
            "Found " + students.size() + " students matching: " + name);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Update student information.
     * 
     * @param id the student ID
     * @param request the update request
     * @return the updated student wrapped in ApiResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> updateStudent(
            @PathVariable String id,
            @Valid @RequestBody StudentCreateRequest request) {
        
        StudentResponse student = studentService.updateStudent(id, request);
        ApiResponse<StudentResponse> response = ApiResponse.success(student, "Student updated successfully");
        
        return ResponseEntity.ok(response);
    }

    /**
     * Delete student by ID.
     * 
     * @param id the student ID
     * @return success message wrapped in ApiResponse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        ApiResponse<Void> response = ApiResponse.success(null, "Student deleted successfully");
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get students born between two dates.
     * 
     * @param startDate the start date (inclusive)
     * @param endDate the end date (inclusive)
     * @return list of students born between the dates wrapped in ApiResponse
     */
    @GetMapping("/birthdate-range")
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getStudentsByBirthDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        
        List<StudentResponse> students = studentService.getStudentsByBirthDateRange(startDate, endDate);
        ApiResponse<List<StudentResponse>> response = ApiResponse.success(students, 
            "Found " + students.size() + " students born between " + startDate + " and " + endDate);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get count of students by program.
     * 
     * @param program the program name
     * @return the count wrapped in ApiResponse
     */
    @GetMapping("/count/program/{program}")
    public ResponseEntity<ApiResponse<Long>> getStudentCountByProgram(
            @PathVariable @NotBlank(message = "Program cannot be blank") String program) {
        
        long count = studentService.getStudentCountByProgram(program);
        ApiResponse<Long> response = ApiResponse.success(count, 
            "Student count for program " + program + ": " + count);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get all students ordered by name.
     * 
     * @return list of students ordered by name wrapped in ApiResponse
     */
    @GetMapping("/ordered-by-name")
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAllStudentsOrderedByName() {
        List<StudentResponse> students = studentService.getAllStudentsOrderedByName();
        ApiResponse<List<StudentResponse>> response = ApiResponse.success(students, 
            "Retrieved " + students.size() + " students ordered by name");
        
        return ResponseEntity.ok(response);
    }

    /**
     * Health check endpoint.
     * 
     * @return simple health status
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() {
        ApiResponse<String> response = ApiResponse.success("OK", "Student service is running");
        return ResponseEntity.ok(response);
    }
}
