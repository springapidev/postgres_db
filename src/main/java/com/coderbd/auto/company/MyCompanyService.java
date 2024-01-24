package com.coderbd.auto.company;


import com.coderbd.auto.MyDynamicReport;
import com.coderbd.auto.MyDynamicReportService;
import com.coderbd.entity.Company;
import com.coderbd.report.domain.report.ExportType;
import com.coderbd.service.CompanyService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MyCompanyService {

    private final MyDynamicReportService dynamicReportService;
    private final CompanyService companyService;

    public MyCompanyService(MyDynamicReportService dynamicReportService, CompanyService companyService) {
        this.dynamicReportService = dynamicReportService;
        this.companyService = companyService;
    }

    public List<MyCompany> getCompanyList() {

        List<MyCompany> companyList = new ArrayList<>();
        for (Company company : this.companyService.findAllBySize(10, 1)) {
            MyCompany myCompany = new MyCompany();
            BeanUtils.copyProperties(company, myCompany);
            companyList.add(myCompany);
        }
        log.info("Company List size {} " + companyList.size());

        return companyList;
    }

    public void exportCompanyReport(ExportType exportType, HttpServletResponse response) throws JRException, IOException {
        var filter = new MyCompanyFilter(exportType);
        var inputStream = filter.getInputStream();
        var title = filter.getReportTitle();
        var list = getCompanyList();

        var report =
                new MyDynamicReport(
                        inputStream,
                        title,
                        exportType,
                        response,
                        filter.generateDynamicColumnAndRows(list));

        dynamicReportService.export(report);
        log.info("End of MyCompanyService::exportTransactionReport()");
    }
}
