package edu.eci.arsw.client_spring_rest.service;

import edu.eci.arsw.client_spring_rest.dto.StudentCreateRequest;
import edu.eci.arsw.client_spring_rest.dto.StudentResponse;
import edu.eci.arsw.client_spring_rest.model.Student;
import edu.eci.arsw.client_spring_rest.repository.StudentRepository;
import edu.eci.arsw.client_spring_rest.exception.StudentNotFoundException;
import edu.eci.arsw.client_spring_rest.exception.DuplicateEmailException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Student business logic.
 * Handles all student-related operations and coordinates between controller and repository layers.
 * 
 * @author Generated
 * @version 1.0
 */
@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Create a new student.
     * 
     * @param request the student creation request
     * @return the created student response
     * @throws DuplicateEmailException if email already exists
     */
    public StudentResponse createStudent(StudentCreateRequest request) {
        // Check if email already exists
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("A student with email " + request.getEmail() + " already exists");
        }

        // Create new student entity
        Student student = new Student(
            request.getName(),
            request.getEmail(),
            request.getBirthDate(),
            request.getProgram()
        );

        // Save to database
        Student savedStudent = studentRepository.save(student);
        
        // Convert to response DTO
        return convertToResponse(savedStudent);
    }

    /**
     * Get all students.
     * 
     * @return list of all students
     */
    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
            .stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }

    /**
     * Get student by ID.
     * 
     * @param id the student ID
     * @return the student response
     * @throws StudentNotFoundException if student not found
     */
    @Transactional(readOnly = true)
    public StudentResponse getStudentById(String id) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));
        
        return convertToResponse(student);
    }

    /**
     * Get student by email.
     * 
     * @param email the student email
     * @return the student response
     * @throws StudentNotFoundException if student not found
     */
    @Transactional(readOnly = true)
    public StudentResponse getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email)
            .orElseThrow(() -> new StudentNotFoundException("Student with email " + email + " not found"));
        
        return convertToResponse(student);
    }

    /**
     * Get students by program.
     * 
     * @param program the program name
     * @return list of students in the program
     */
    @Transactional(readOnly = true)
    public List<StudentResponse> getStudentsByProgram(String program) {
        return studentRepository.findByProgram(program)
            .stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }

    /**
     * Search students by name (case-insensitive).
     * 
     * @param name the name or partial name to search for
     * @return list of matching students
     */
    @Transactional(readOnly = true)
    public List<StudentResponse> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name)
            .stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }

    /**
     * Update student information.
     * 
     * @param id the student ID
     * @param request the update request
     * @return the updated student response
     * @throws StudentNotFoundException if student not found
     * @throws DuplicateEmailException if email already exists for another student
     */
    public StudentResponse updateStudent(String id, StudentCreateRequest request) {
        Student existingStudent = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));

        // Check if email is being changed and if new email already exists
        if (!existingStudent.getEmail().equals(request.getEmail()) && 
            studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("A student with email " + request.getEmail() + " already exists");
        }

        // Update student fields
        existingStudent.setName(request.getName());
        existingStudent.setEmail(request.getEmail());
        existingStudent.setBirthDate(request.getBirthDate());
        existingStudent.setProgram(request.getProgram());
        existingStudent.setUpdatedAt(LocalDateTime.now());

        // Save updated student
        Student updatedStudent = studentRepository.save(existingStudent);
        
        return convertToResponse(updatedStudent);
    }

    /**
     * Delete student by ID.
     * 
     * @param id the student ID
     * @throws StudentNotFoundException if student not found
     */
    public void deleteStudent(String id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }
        
        studentRepository.deleteById(id);
    }

    /**
     * Get students born between two dates.
     * 
     * @param startDate the start date (inclusive)
     * @param endDate the end date (inclusive)
     * @return list of students born between the dates
     */
    @Transactional(readOnly = true)
    public List<StudentResponse> getStudentsByBirthDateRange(LocalDate startDate, LocalDate endDate) {
        return studentRepository.findByBirthDateBetween(startDate, endDate)
            .stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }

    /**
     * Get count of students by program.
     * 
     * @param program the program name
     * @return the count of students in the program
     */
    @Transactional(readOnly = true)
    public long getStudentCountByProgram(String program) {
        return studentRepository.countByProgram(program);
    }

    /**
     * Get all students ordered by name.
     * 
     * @return list of students ordered by name
     */
    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudentsOrderedByName() {
        return studentRepository.findAllOrderByName()
            .stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }

    /**
     * Convert Student entity to StudentResponse DTO.
     * 
     * @param student the student entity
     * @return the student response DTO
     */
    private StudentResponse convertToResponse(Student student) {
        return new StudentResponse(
            student.getId(),
            student.getName(),
            student.getEmail(),
            student.getBirthDate(),
            student.getProgram(),
            student.getCreatedAt(),
            student.getUpdatedAt()
        );
    }
}
