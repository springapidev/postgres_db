package com.coderbd.service;

import com.coderbd.entity.Company;
import com.coderbd.exceptions.CompanyNotFoundException;
import com.coderbd.exceptions.CompanyServiceBusinessException;
import com.coderbd.repository.CompanyRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "companies")
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository repository;

//    @PostConstruct
//    private void initDB() {
//        List<Company> list = IntStream.rangeClosed(1, 50000).mapToObj(i -> new Company((long) i, "notNull" + i, "notempty" + i, "not blank" + i, "12345678911", "abc@gmail.com", 6, "asdfghj", LocalDate.now().minusYears(2), LocalDate.now().minusYears(20), "LIVE")).collect(Collectors.toList());
//        this.repository.saveAll(list);
//    }

    @CachePut(key = "#company.id")
    @Override
    public Company save(Company company) {
        log.info("CompanyService::save is connecting to DB");
        try {
            return this.repository.save(company);
        } catch (Exception e) {
            throw new CompanyServiceBusinessException("Save Failed....");
        }

    }

    @Cacheable(key = "#id")
    @Override
    public Company findById(Long id) {
        log.info("CompanyService::findById is connecting to DB");
        Company company = this.repository.findById(id).orElseThrow(() -> new CompanyNotFoundException("Company does not exists by ID: " + id));

        return company;
    }

    @Cacheable
    @Override
    public List<Company> findAll() {
        log.info("CompanyService::findAll is connecting to DB");

        return repository.findAll();
    }
    @Cacheable
    @Override
    public List<Company> findAllBySize(int size, int page) {
        log.info("CompanyService::findAll is connecting to DB");
        Pageable pageable = PageRequest.of(page, size);
        Page<Company> companies=this.repository.findAll(pageable);
        return companies.getContent();
    }
}
