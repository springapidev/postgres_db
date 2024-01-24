package com.coderbd.service;

import com.coderbd.entity.Company;
import com.coderbd.report.domain.TransactionFilter;
import com.coderbd.report.domain.report.ExportType;
import com.coderbd.report.domain.report.dynamic.DynamicReport;
import com.coderbd.report.service.report.ReportService;
import com.coderbd.report.service.report.dynamic.DynamicReportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
@Slf4j
@CacheConfig(cacheNames = "companies")
public class ReportServiceImpl implements MyReportService {
    private final CompanyService service;
    private final ReportService reportService;
    private final DynamicReportService dynamicReportService;
    public void downloadCompanyReport(ExportType exportType, HttpServletResponse response) throws JRException, IOException {
        List<Company> companyList = service.findAllBySize(100,2);
        reportService.exportReport(companyList, exportType, response);
    }

    @Cacheable(key = "#reportType")
    @Override
    public String exportReport(String reportType, int page, int size, HttpServletResponse response) throws JRException, IOException {
        log.info("Export Started");

        String path = "F:\\reza\\jasper_report_output";
        List<Company> companies = this.service.findAllBySize(size, page);
        File file = ResourceUtils.getFile("classpath:company_all.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(companies);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Mohammad Rajaul Islam");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportType.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\company_all.pdf");
        } else if (reportType.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\company_all.html");
        } else if (reportType.equalsIgnoreCase("excel")) {
            String titleTransactionBy="company_data";
            var fileName = titleTransactionBy.replace(" ", "") + LocalDate.now();
            JRXlsxExporter exporter = new JRXlsxExporter();
            SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
            reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
            reportConfigXLS.setDetectCellType(true);
            reportConfigXLS.setCollapseRowSpan(false);
            exporter.setConfiguration(reportConfigXLS);
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            response.setHeader(
                    HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "fileName" + ".xlsx;");
            response.setContentType("application/octet-stream");
            exporter.exportReport();
        }

        log.info("Expert Done");
        return "Report Generated at path {} " + path;
    }

    public void exportCompanyReportDynamic(ExportType exportType, HttpServletResponse response) throws JRException, IOException {
        var filter = new TransactionFilter(exportType);
        var inputStream = filter.getInputStream();
        var title = filter.getReportTitle();
        var list = this.service.findAllBySize(100,2);

        var report =
                new DynamicReport(
                        inputStream,
                        title,
                        exportType,
                        response,
                        filter.generateDynamicColumnAndRowsForCompany(list));

        dynamicReportService.export(report);

        return;
    }
}













































