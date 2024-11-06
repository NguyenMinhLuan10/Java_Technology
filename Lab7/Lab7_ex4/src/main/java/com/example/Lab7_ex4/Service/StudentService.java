package com.example.Lab7_ex4.Service;

import com.example.Lab7_ex4.Model.Student;
import com.example.Lab7_ex4.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        student.setEmail(studentDetails.getEmail());
        student.setIeltsScore(studentDetails.getIeltsScore());
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsByAge(int age) {
        return studentRepository.findByAgeGreaterThanEqual(age);
    }

    public long countStudentsByIeltsScore(double ieltsScore) {
        return studentRepository.countByIeltsScore(ieltsScore);
    }

    public List<Student> getStudentsByNameContaining(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

}
