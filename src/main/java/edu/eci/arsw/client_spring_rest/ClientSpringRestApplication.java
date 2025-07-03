package edu.eci.arsw.client_spring_rest;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Main Spring Boot application class.
 * Configures the application and loads environment variables.
 * 
 * @author Generated
 * @version 1.0
 */
@SpringBootApplication
@EnableMongoRepositories
public class ClientSpringRestApplication {

	public static void main(String[] args) {
		// Load environment variables from .env file
		loadEnvironmentVariables();
		
		SpringApplication.run(ClientSpringRestApplication.class, args);
	}

	/**
	 * Load environment variables from .env file and set them as system properties.
	 */
	private static void loadEnvironmentVariables() {
		try {
			Dotenv dotenv = Dotenv.configure()
					.directory("./")
					.ignoreIfMalformed()
					.ignoreIfMissing()
					.load();

			if (dotenv != null) {
				// Set MongoDB URI
				if (dotenv.get("MONGODB_URI") != null) {
					System.setProperty("MONGODB_URI", dotenv.get("MONGODB_URI"));
				}
				
				// Set MongoDB Database
				if (dotenv.get("MONGODB_DATABASE") != null) {
					System.setProperty("MONGODB_DATABASE", dotenv.get("MONGODB_DATABASE"));
				}
				
				// Set Server Port
				if (dotenv.get("SERVER_PORT") != null) {
					System.setProperty("SERVER_PORT", dotenv.get("SERVER_PORT"));
				}
				
				// Set CORS Allowed Origins
				if (dotenv.get("CORS_ALLOWED_ORIGINS") != null) {
					System.setProperty("cors.allowed.origins", dotenv.get("CORS_ALLOWED_ORIGINS"));
				}
				
				// Set Spring Profile
				if (dotenv.get("SPRING_PROFILES_ACTIVE") != null) {
					System.setProperty("spring.profiles.active", dotenv.get("SPRING_PROFILES_ACTIVE"));
				}
				
				System.out.println("Environment variables loaded successfully from .env file");
			}
		} catch (Exception e) {
			System.out.println("Warning: Could not load .env file. Using system environment variables. Error: " + e.getMessage());
		}
	}
}
