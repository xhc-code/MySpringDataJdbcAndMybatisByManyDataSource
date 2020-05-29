package org.example.service.impl;

import org.example.annon.TargetDataSource;
import org.example.domain.Student;
import org.example.mapper.StudentMapper;
import org.example.service.StudentService;
import org.example.utils.ContextSwitchDataSourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
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

    @Override
    public void save() {

        Student student = Student.builder().name("xiaoW").age(25).sex("0").build();
        studentMapper.save(student);
        //抛出Zero异常，测试事务是否存在并运行正常
//        int i = 1/0;
        Student student1 = Student.builder().name("xiaoO").age(24).sex("1").build();
        studentMapper.save(student1);
    }
}
