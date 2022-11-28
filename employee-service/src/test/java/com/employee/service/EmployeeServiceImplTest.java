package com.employee.service;

import com.employee.entity.CompanyEntity;
import com.employee.entity.EmployeeEntity;
import com.employee.model.Address;
import com.employee.model.Company;
import com.employee.model.Employee;
import com.employee.model.Salary;
import com.employee.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void createsNewEmployeeSuccessfullyAndVerifiesCompanyName() {

        Employee employee = new Employee(2,"empName","surname","email",new Address("street","landmark","51143"),20.0,new Company(2,""));
        employeeService.save(employee);
        ArgumentCaptor<EmployeeEntity> employeeEntityArgumentCaptor = ArgumentCaptor.forClass(EmployeeEntity.class);
        verify(employeeRepository, times(1)).save(employeeEntityArgumentCaptor.capture());
        EmployeeEntity employeeEntity = employeeEntityArgumentCaptor.getValue();
        assertNotNull(employeeEntity);
        assertSame("empName", employeeEntity.getName());
        assertSame("surname", employeeEntity.getSurname());
        assertSame("email", employeeEntity.getEmail());
        assertSame("street", employeeEntity.getAddress().getStreet());
        assertSame("landmark", employeeEntity.getAddress().getLandmark());
    }

    @Test
    public void getsAllEmployeesSuccessfully() {

        EmployeeEntity employee1 = new EmployeeEntity(2,"empName1","surname","email",new Address("street","landmark","51143"),20.0,new CompanyEntity(2,""));
        EmployeeEntity employee2 = new EmployeeEntity(2,"empName2","surname","email",new Address("street","landmark","51143"),20.0,new CompanyEntity(2,""));
        List<EmployeeEntity> employeeEntityList = new ArrayList<>();
        employeeEntityList.add(employee1);
        employeeEntityList.add(employee2);
        when(employeeRepository.findAll()).thenReturn(employeeEntityList);
        List<Employee> employeeList = employeeService.getAll();
        assertNotNull(employeeList);
        assertSame("empName1", employeeList.get(0).getName());
        assertSame("empName2", employeeList.get(1).getName());
    }

    @Test
    public void getsEmployeeByIdSuccessfully(){
        EmployeeEntity employee1 = new EmployeeEntity(2,"empName1","surname","email",new Address("street","landmark","51143"),20.0,new CompanyEntity(2,""));
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee1));
        Employee employee = employeeService.getById(1);
        assertNotNull(employee);
        assertSame(employee.getName(), "empName1");
    }

    @Test
    public void getsAverageSalarySuccessfully(){
        when(employeeRepository.getAverageSalary(anyInt())).thenReturn(Optional.of(290.0));
        Salary averageSalary = employeeService.getAverageSalary(1);
        assertNotNull(averageSalary);
        assertEquals(averageSalary.getAverageSalary(), 290.0, 0);
    }

    @Test
    public void deletesEmployeeSuccessfullyById(){
        employeeService.delete(2);
        ArgumentCaptor<Integer> employeeEntityArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(employeeRepository, times(1)).deleteById(employeeEntityArgumentCaptor.capture());
        Integer employeeId = employeeEntityArgumentCaptor.getValue();
        assertEquals(2, employeeId.intValue());
    }


}
