package edu.eci.arsw.client_spring_rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Integration tests for the Spring Boot application.
 * 
 * @author Generated
 * @version 1.0
 */
@SpringBootTest
@TestPropertySource(properties = {
	"spring.data.mongodb.uri=mongodb://localhost:27017/test_db",
	"spring.data.mongodb.database=test_db"
})
class ClientSpringRestApplicationTests {

	/**
	 * Test that the Spring context loads successfully.
	 */
	@Test
	void contextLoads() {
		// This test will pass if the application context loads successfully
	}

}
