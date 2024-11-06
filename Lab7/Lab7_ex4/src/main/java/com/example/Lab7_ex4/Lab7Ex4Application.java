package com.example.Lab7_ex4;

import com.example.Lab7_ex4.Model.Student;
import com.example.Lab7_ex4.Service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Lab7Ex4Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab7Ex4Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentService studentService) {
		return args -> {

			//
			System.out.println("Students with age greater than or equal to 21:");
			List<Student> studentsByAge = studentService.getStudentsByAge(21);
			studentsByAge.forEach(System.out::println);

			//
			long countIeltsScore = studentService.countStudentsByIeltsScore(6.5);
			System.out.println("Number of students with IELTS score of 6.5: " + countIeltsScore);

			//
			System.out.println("Students whose name contains 'a':");
			List<Student> studentsByName = studentService.getStudentsByNameContaining("a");
			studentsByName.forEach(System.out::println);
		};
	}
}
