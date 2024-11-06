package com.example.Lab7_ex6.Repository;


import com.example.Lab7_ex6.Model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagedStudentRepository extends PagingAndSortingRepository<Student, Long> {

    Iterable<Student> findAll(Sort sort);

    Page<Student> findAll(Pageable pageable);
}