package com.employee.service;

import com.employee.entity.CompanyEntity;
import com.employee.exception.DataNotFoundException;
import com.employee.model.Company;
import com.employee.repository.CompanyRepository;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Integer save(final Company company) {

        CompanyEntity companyEntity = new CompanyEntity();
        BeanUtils.copyProperties(company, companyEntity);
        companyRepository.save(companyEntity);
        return companyEntity.getId();
    }

    @Override
    public Company getById(final Integer id) throws DataNotFoundException {

        Optional<CompanyEntity> companyEntity = companyRepository.findById(id);
        CompanyEntity companyFetched = companyEntity.orElseThrow(() -> new DataNotFoundException("Company not found in the system"));
        Company company = new Company();
        modelMapper.map(companyFetched, company);
        return company;
    }

    public List<Company> getAll(){
        List<CompanyEntity> companyEntities = companyRepository.findAll();
        List<Company> characters = modelMapper.map(companyEntities, new TypeToken<List<Company>>() {}.getType());
        return characters;
    }

}