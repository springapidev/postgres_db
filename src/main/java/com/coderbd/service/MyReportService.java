package com.coderbd.service;

import com.coderbd.report.domain.report.ExportType;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface MyReportService {
    String exportReport(String ReportType, int page, int size, HttpServletResponse response) throws IOException, JRException;
    void downloadCompanyReport(ExportType exportType, HttpServletResponse response) throws JRException, IOException;
    void exportCompanyReportDynamic(ExportType exportType, HttpServletResponse response) throws JRException, IOException;
}
