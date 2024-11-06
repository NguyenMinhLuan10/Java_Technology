package com.example.Lab7_ex4.Repository;

import com.example.Lab7_ex4.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAgeGreaterThanEqual(int age);

    long countByIeltsScore(double ieltsScore);

    List<Student> findByNameContainingIgnoreCase(String name);

}
