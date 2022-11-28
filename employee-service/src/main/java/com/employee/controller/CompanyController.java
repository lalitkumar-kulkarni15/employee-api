package com.employee.controller;

import com.employee.model.Company;
import com.employee.service.CompanyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@Validated
public class CompanyController {

    private CompanyService companyService;

    CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @PostMapping("/company")
    public Integer save(@RequestBody @Valid final Company company){
        return companyService.save(company);
    }

    @GetMapping("/company/{id}")
    public Company get(@PathVariable Integer id){
        return companyService.getById(id);
    }

    @GetMapping("/company")
    public List<Company> get(){
        return companyService.getAll();
    }

}
