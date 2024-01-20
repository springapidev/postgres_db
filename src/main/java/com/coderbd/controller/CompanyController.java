package com.coderbd.controller;

import com.coderbd.entity.Company;
import com.coderbd.exceptions.CompanyServiceBusinessException;
import com.coderbd.exceptions.ServiceResponse;
import com.coderbd.service.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/company/")
public class CompanyController {
    private final CompanyService service;


    @PostMapping(value = "add")
    public ServiceResponse<Company> add(@RequestBody @Valid Company company) {
        ServiceResponse<Company> serviceResponse = new ServiceResponse<>();
        Company company1 = null;
        try {
            company1 = this.service.save(company);
            serviceResponse.setResponse(company1);
            serviceResponse.setStatus(HttpStatus.OK);
            serviceResponse.setCode(200);
            return serviceResponse;
        } catch (Exception exception) {
            throw new CompanyServiceBusinessException("Company Save Failed.....");
        }

    }

    @GetMapping(value = "one/{id}")
    public ServiceResponse<Company> findById(@PathVariable(name = "id") Long id) {

            ServiceResponse<Company> serviceResponse=new ServiceResponse<>();
            Company company = this.service.findById(id);
            serviceResponse.setResponse(company);
            serviceResponse.setStatus(HttpStatus.FOUND);
            serviceResponse.setCode(302);
            return serviceResponse;

    }

    @GetMapping(value = "log")
    public String log(){
        log.trace("Trace Message");
        log.debug("debug Message");
        log.info("info Message");
        log.warn("warn Message");
        log.error("Error Message");
        return "Log Printed";
    }
}
