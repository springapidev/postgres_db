package com.coderbd.service;

import com.coderbd.entity.Company;

import java.util.List;

public interface CompanyService {
    Company save(Company company);
    Company findById(Long id);
    List<Company> findAll();
    List<Company> findAllBySize(int size, int page);
}
