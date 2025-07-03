package edu.eci.arsw.client_spring_rest.repository;

import edu.eci.arsw.client_spring_rest.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Student entity.
 * Extends MongoRepository to provide basic CRUD operations and custom query methods.
 * 
 * @author Generated
 * @version 1.0
 */
@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    /**
     * Find a student by email address.
     * 
     * @param email the email address to search for
     * @return Optional containing the student if found, empty otherwise
     */
    Optional<Student> findByEmail(String email);

    /**
     * Find students by program.
     * 
     * @param program the program name to search for
     * @return List of students in the specified program
     */
    List<Student> findByProgram(String program);

    /**
     * Find students by name containing the specified string (case-insensitive).
     * 
     * @param name the name or partial name to search for
     * @return List of students whose names contain the search string
     */
    List<Student> findByNameContainingIgnoreCase(String name);

    /**
     * Find students born between two dates.
     * 
     * @param startDate the start date (inclusive)
     * @param endDate the end date (inclusive)
     * @return List of students born between the specified dates
     */
    List<Student> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);

    /**
     * Check if a student with the given email exists.
     * 
     * @param email the email to check
     * @return true if a student with the email exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Count students by program.
     * 
     * @param program the program name
     * @return the number of students in the program
     */
    long countByProgram(String program);

    /**
     * Find all students ordered by name ascending.
     * 
     * @return List of all students ordered by name
     */
    @Query("{ $query: {}, $orderby: { name: 1 } }")
    List<Student> findAllOrderByName();

    /**
     * Find students by program ordered by birth date descending.
     * 
     * @param program the program name
     * @return List of students in the program ordered by birth date (newest first)
     */
    List<Student> findByProgramOrderByBirthDateDesc(String program);

    /**
     * Delete students by program.
     * 
     * @param program the program name
     * @return the number of deleted students
     */
    long deleteByProgram(String program);
}
