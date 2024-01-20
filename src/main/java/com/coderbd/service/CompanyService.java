package com.coderbd.service;

import com.coderbd.entity.Company;

public interface CompanyService {
    Company save(Company company);
    Company findById(Long id);
}
