package com.example.Lab7_ex2;

import com.example.Lab7_ex2.Model.Student;
import com.example.Lab7_ex2.Repository.StudentRepository;
import com.example.Lab7_ex2.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Lab7Ex2Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab7Ex2Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentService studentService) {
		return args -> {
			//Thêm sinh viên mới
			studentService.saveStudent(new Student(null, "Minh Luân", 20, "alice@example.com", 6.5));
			studentService.saveStudent(new Student(null, "Cao Kỳ", 22, "bob@example.com", 7.0));
			studentService.saveStudent(new Student(null, "Tấn Nhã", 21, "charlie@example.com", 7.5));
			System.out.println("Students added to the database.");

			System.out.println("List of students:");
			studentService.getAllStudents().forEach(System.out::println);

			//Cập nhật sinh viên 1
			Student studentToUpdate = studentService.getStudentById(1L).orElse(null);
			if (studentToUpdate != null) {
				studentToUpdate.setAge(23);
				studentToUpdate.setIeltsScore(8.0);
				studentService.updateStudent(1L, studentToUpdate);
				System.out.println("Updated student information:");
				System.out.println(studentService.getStudentById(1L).orElse(null));
			}

			//Xóa sinh viên 2
			studentService.deleteStudent(2L);
			System.out.println("Deleted student with ID 2. Updated list of students:");
			studentService.getAllStudents().forEach(System.out::println);


		};
	}
}
