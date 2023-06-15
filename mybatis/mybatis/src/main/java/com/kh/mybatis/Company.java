package com.kh.mybatis;

import lombok.Data;

import java.util.List;

@Data // @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode 를 한번에 설정해줌
public class Company {
    private int id;
    private String name;
    private String address;
    private List<Employee> employeeList;
}
