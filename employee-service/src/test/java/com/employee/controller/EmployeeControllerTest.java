package com.employee.controller;

import com.employee.EmployeeServiceApplication;
import com.employee.model.Address;
import com.employee.model.Company;
import com.employee.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {EmployeeServiceApplication.class})
@TestPropertySource(locations = "classpath:test.properties")
public class EmployeeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    @Value("${application.test.host}")
    private String host;

    private HttpHeaders httpHeaders;

    @Test
    public void createsNewEmployee() {
        Employee employee = new Employee(2,"empName","surname","email",new Address("street","landmark","51143"),20.0,new Company(2,""));
        HttpEntity<Employee> employeeRequest = new HttpEntity<Employee>(employee, getHttpHeader());
        ResponseEntity<Integer> response = restTemplate.exchange(createURLWithPort("/employee", host, port), HttpMethod.POST, employeeRequest, Integer.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void updatesExistingEmployee() {
        Employee employee = new Employee(1,"empName","surname","email",new Address("street","landmark","51143"),20.0,new Company(2,""));
        HttpEntity<Employee> employeeRequest = new HttpEntity<Employee>(employee, getHttpHeader());
        ResponseEntity<Integer> response = restTemplate.exchange(createURLWithPort("/employee", host, port), HttpMethod.POST, employeeRequest, Integer.class);

        Employee employeeUpdated = new Employee(1,"empNameUpdated","surname","email",new Address("street","landmark","51143"),20.0,new Company(2,""));
        HttpEntity<Employee> employeeUpdateRequest = new HttpEntity<Employee>(employeeUpdated, getHttpHeader());
        ResponseEntity<Employee> responseUpdated = restTemplate.exchange(createURLWithPort("/employee", host, port), HttpMethod.PUT, employeeUpdateRequest, Employee.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("empNameUpdated", responseUpdated.getBody().getName());
    }

    @Test
    public void getsAllEmployeeSuccessfully() {

    }

    private HttpHeaders getHttpHeader(){
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediatypeList = new ArrayList<>();
        mediatypeList.add(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(mediatypeList);
        return httpHeaders;
    }

    public static String createURLWithPort(final String uri, final String host,
                                           final String port) {
        return "http://" + host + ":" + port + uri;
    }
}
