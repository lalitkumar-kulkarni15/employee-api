package com.employee.model;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private Address address;
    private double salary;
    private Company company;

}
