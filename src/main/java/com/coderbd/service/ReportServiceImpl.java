package com.coderbd.service;

import com.coderbd.entity.Company;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
@Slf4j
@CacheConfig(cacheNames = "companies")
public class ReportServiceImpl implements ReportService {
    private final CompanyService service;

    @Cacheable(key = "#reportType")
    @Override
    public String exportReport(String reportType, int page, int size) throws FileNotFoundException, JRException {
        log.info("Export Started");
        String path = "C:\\Users\\dell\\Documents\\jasper_reports";
        List<Company> companies = this.service.findAllBySize(size, page);
        File file = ResourceUtils.getFile("classpath:company_all.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(companies);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Mohammad Rajaul Islam");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportType.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\company_all.pdf");
        }

        log.info("Expert Done");
        return "Report Generated at path {} " + path;
    }
}













































