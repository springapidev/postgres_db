package com.coderbd.controller;

import com.coderbd.entity.Company;
import com.coderbd.exceptions.CompanyServiceBusinessException;
import com.coderbd.exceptions.ServiceResponse;
import com.coderbd.report.domain.report.ExportType;
import com.coderbd.service.CompanyService;
import com.coderbd.service.MyReportService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/company/")
public class CompanyController {
    private final CompanyService service;
    private final MyReportService reportService;


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
    @GetMapping(value = "list")
    public ServiceResponse<List<Company>> findAll() {

        ServiceResponse<List<Company>> serviceResponse=new ServiceResponse<>();
        List<Company> company = this.service.findAll();
        serviceResponse.setResponse(company);
        serviceResponse.setStatus(HttpStatus.FOUND);
        serviceResponse.setCode(302);
        return serviceResponse;

    }
    @GetMapping(value = "all/{size}/{page}")
    public ServiceResponse<List<Company>> findAllBySizeAndPage(@PathVariable int size, @PathVariable int page) {

        ServiceResponse<List<Company>> serviceResponse=new ServiceResponse<>();
        List<Company> company = this.service.findAllBySize(size,page);
        serviceResponse.setResponse(company);
        serviceResponse.setStatus(HttpStatus.FOUND);
        serviceResponse.setCode(302);
        return serviceResponse;
    }
    @RequestMapping(value = "export",method = RequestMethod.GET)
    public void exportJasperReport(@RequestParam(value = "exportType") ExportType exportType, HttpServletResponse response) throws JRException, IOException {
        this.reportService.downloadCompanyReport(exportType,response);
    }
    @RequestMapping(value = "export-dynamic",method = RequestMethod.GET)
    public void exportJasperReportDynamic(@RequestParam(value = "exportType") ExportType exportType, HttpServletResponse response) throws JRException, IOException {
        this.reportService.exportCompanyReportDynamic(exportType,response);
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
