package org.example.service.impl;

import org.example.annon.TargetDataSource;
import org.example.domain.Student;
import org.example.mapper.StudentMapper;
import org.example.service.StudentService;
import org.example.utils.ContextSwitchDataSourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private @Autowired StudentMapper studentMapper;

    @TargetDataSource(ContextSwitchDataSourceUtils.DataSourceEnum.ONE)
    @Override
    public Optional<Student> findById(String id) {
        test();
        return studentMapper.findById(id);
    }

    @TargetDataSource(ContextSwitchDataSourceUtils.DataSourceEnum.TWO)
    @Override
    public void test(){
        System.out.println("Service Implement 的test方法执行了");
    }
}
