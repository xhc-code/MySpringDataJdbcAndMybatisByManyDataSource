package org.example.service;

import org.example.annon.TargetDataSource;
import org.example.domain.Student;
import org.example.utils.ContextSwitchDataSourceUtils;

import java.util.Optional;

public interface StudentService {

    Optional<Student> findById(String id);
    public void test();
}
