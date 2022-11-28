package com.employee.service;

import com.employee.entity.CompanyEntity;
import com.employee.model.Company;
import com.employee.repository.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Test
    public void createsNewCompanySuccessfullyAndVerifiesCompanyName() {

        companyService.save(new Company(2,"company"));
        ArgumentCaptor<CompanyEntity> companyEntityArgumentCaptor = ArgumentCaptor.forClass(CompanyEntity.class);
        verify(companyRepository, times(1)).save(companyEntityArgumentCaptor.capture());
        CompanyEntity companyEntity = companyEntityArgumentCaptor.getValue();
        assertNotNull(companyEntity);
        assertSame("company", companyEntity.getCompanyName());
    }

}
