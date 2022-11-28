package com.employee.repository;

import com.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    @Query("select avg(e.salary) from EmployeeEntity e where e.company.id=?1")
    public Optional<Double> getAverageSalary(Integer id);
}
