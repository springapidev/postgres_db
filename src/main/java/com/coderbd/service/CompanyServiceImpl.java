package com.coderbd.service;

import com.coderbd.entity.Company;
import com.coderbd.exceptions.CompanyNotFoundException;
import com.coderbd.exceptions.CompanyServiceBusinessException;
import com.coderbd.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository repository;

    @Override
    public Company save(Company company) {
        try {
            return this.repository.save(company);
        } catch (Exception e) {
            throw new CompanyServiceBusinessException("Save Failed....");
        }

    }

    @Override
    public Company findById(Long id) {

        Company company=this.repository.findById(id).orElseThrow(() -> new CompanyNotFoundException("Company does not exists by ID: " + id));

        return company;
    }
}
