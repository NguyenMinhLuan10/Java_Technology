package com.example.Lab7_ex6.Service;

import com.example.Lab7_ex6.Model.Student;
import com.example.Lab7_ex6.Repository.PagedStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final PagedStudentRepository pagedStudentRepository;

    @Autowired
    public StudentService(PagedStudentRepository pagedStudentRepository) {
        this.pagedStudentRepository = pagedStudentRepository;
    }

    public List<Student> getStudentsSortedByAgeAndIeltsScore() {
        Sort sort = Sort.by(Sort.Order.desc("age"), Sort.Order.asc("ieltsScore"));
        return (List<Student>) pagedStudentRepository.findAll(sort);
    }

    public List<Student> getStudentsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Student> studentPage = pagedStudentRepository.findAll(pageable);
        return studentPage.getContent();
    }
}
