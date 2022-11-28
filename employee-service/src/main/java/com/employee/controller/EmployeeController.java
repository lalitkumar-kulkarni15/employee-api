package com.employee.controller;

import com.employee.model.Employee;
import com.employee.model.Salary;
import com.employee.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@Api(tags = "Employee API", value = "This API contains rest endpoints to create, update,delete,read employee resource")
public class EmployeeController {

    private EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new Employee.", notes = "This API creates a new Employee resource")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Something went wrong in the service, please "
            + "contact the system administrator - Email - lalitkulkarniofficial@gmail.com"),
            @ApiResponse(code = 201, message = "Employee has been successfully created in the system.")})
    @ApiResponse(code = 400, message = "Bad input request.Please check the error description for more details.")
    public Integer save(@RequestBody @Valid final Employee employee){
        return employeeService.save(employee);
    }

    @ApiOperation(value = "Update an existing Employee.", notes = "This API updates an existing Employee resource")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Something went wrong in the service, please "
            + "contact the system administrator - Email - lalitkulkarniofficial@gmail.com"),
            @ApiResponse(code = 200, message = "Employee has been successfully updated in the system.")})
    @ApiResponse(code = 400, message = "Bad input request.Please check the error description for more details.")
    @PutMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody @Valid final Employee employee){
        employeeService.save(employee);
        return employee;
    }

    @GetMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Gets an existing Employee by id.", notes = "This API gets an existing Employee resource by id")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Something went wrong in the service, please "
            + "contact the system administrator - Email - lalitkulkarniofficial@gmail.com"),
            @ApiResponse(code = 200, message = "Employee has been successfully fetched from the system.")})
    @ApiResponse(code = 400, message = "Bad input request.Please check the error description for more details.")
    public Employee get(@PathVariable Integer id){
        return employeeService.getById(id);
    }

    @GetMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all existing Employees.", notes = "This API gets all existing Employee resource")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Something went wrong in the service, please "
            + "contact the system administrator - Email - lalitkulkarniofficial@gmail.com"),
            @ApiResponse(code = 200, message = "Employees has been successfully fetched from the system.")})
    @ApiResponse(code = 400, message = "Bad input request.Please check the error description for more details.")
    public List<Employee> get(){
        return employeeService.getAll();
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Deletes an existing Employee by id.", notes = "This API deletes an existing Employee resource by id")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Something went wrong in the service, please "
            + "contact the system administrator - Email - lalitkulkarniofficial@gmail.com"),
            @ApiResponse(code = 200, message = "Employee has been successfully deleted from the system.")})
    @ApiResponse(code = 400, message = "Bad input request.Please check the error description for more details.")
    public void delete(@PathVariable Integer id){
        employeeService.delete(id);
    }

    @ApiOperation(value = "Gets the average salary of the employees in a company.", notes = "This API gets an average salary of Employees in a company")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Something went wrong in the service, please "
            + "contact the system administrator - Email - lalitkulkarniofficial@gmail.com"),
            @ApiResponse(code = 200, message = "Employee average salary has been successfully fetched.")})
    @ApiResponse(code = 400, message = "Bad input request.Please check the error description for more details.")
    @GetMapping("/employee/salary/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Salary getSalary(@PathVariable Integer id){
        return employeeService.getAverageSalary(id);
    }

}
