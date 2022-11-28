package com.employee.service;

import com.employee.exception.DataNotFoundException;
import com.employee.model.Company;
import java.util.List;

public interface CompanyService {

    Integer save(final Company company);
    Company getById(final Integer id) throws DataNotFoundException;

    List<Company> getAll();
}
