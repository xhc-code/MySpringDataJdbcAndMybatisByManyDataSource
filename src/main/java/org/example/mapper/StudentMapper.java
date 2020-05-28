package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.Student;
import org.springframework.data.repository.CrudRepository;

@Mapper
public interface StudentMapper extends CrudRepository<Student,String> {

}
