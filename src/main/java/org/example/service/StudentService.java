package org.example.service;

import org.example.domain.Student;

import java.util.Optional;

public interface StudentService {

    Optional<Student> findById(String id);
    void test();
    void save();
}
