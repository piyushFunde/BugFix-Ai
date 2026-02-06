package com.company.bugfixai;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BugfixAiServiceApplication {

	public static void main(String[] args) {
		try {
			System.out.println("Starting BugfixAiServiceApplication...");
			SpringApplication.run(BugfixAiServiceApplication.class, args);
			System.out.println("BugfixAiServiceApplication started successfully!");
		} catch (Exception e) {
			System.err.println("Failed to start application: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
