package org.example.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Student {

    @Id
    private String id;

    private String name;

    private String sex;

    private Integer age;

}
