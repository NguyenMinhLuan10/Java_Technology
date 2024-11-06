package com.example.Lab7_ex5;

import com.example.Lab7_ex5.Model.Student;
import com.example.Lab7_ex5.Service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Lab7Ex5Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab7Ex5Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentService studentService) {
		return args -> {

			System.out.println("Students with age greater than or equal to 21:");
			List<Student> studentsByAgeQuery = studentService.getStudentsByAgeQuery(21);
			studentsByAgeQuery.forEach(System.out::println);

			long countIeltsScoreQuery = studentService.countStudentsByIeltsScoreQuery(6.5);
			System.out.println("Number of students with IELTS score of 6.5: " + countIeltsScoreQuery);

			System.out.println("Students whose name contains 'a':");
			List<Student> studentsByNameQuery = studentService.getStudentsByNameContainingQuery("a");
			studentsByNameQuery.forEach(System.out::println);
		};
	}

}
