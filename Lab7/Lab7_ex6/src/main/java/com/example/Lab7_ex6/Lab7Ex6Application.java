package com.example.Lab7_ex6;

import com.example.Lab7_ex6.Model.Student;
import com.example.Lab7_ex6.Service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab7Ex6Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab7Ex6Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentService studentService) {
		return args -> {

			System.out.println("Students sorted by age (desc) and IELTS score (asc):");
			studentService.getStudentsSortedByAgeAndIeltsScore().forEach(System.out::println);

			System.out.println("Students in positions 4-5-6:");
			int page = 1;
			int pageSize = 3;
			studentService.getStudentsByPage(page, pageSize).forEach(System.out::println);
		};
	}

}
