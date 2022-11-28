package com.employee.service;

import com.employee.model.Employee;
import com.employee.model.Salary;
import java.util.List;

public interface EmployeeService {

    int save(final Employee employee);
    List<Employee> getAll();
    Employee getById(Integer id);
    void delete(Integer id);

    public Salary getAverageSalary(Integer id);
}
