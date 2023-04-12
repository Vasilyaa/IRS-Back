package net.redciscso.javapmproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//создали и наcтроили application context спринга
@SpringBootApplication
@EnableWebMvc
public class JavaProject {

	public static void main(String[] args) {
		SpringApplication.run(JavaProject.class, args);
	}

}
