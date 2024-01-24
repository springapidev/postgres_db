package com.coderbd.auto;

import com.coderbd.report.domain.report.ExportType;
import com.coderbd.report.domain.report.Report;
import jakarta.servlet.http.HttpServletResponse;

import java.io.InputStream;

public class MyDynamicReport extends Report {
  private final MyDynamicReportProperties dynamicReportProperties;

  public MyDynamicReport(
      InputStream inputStream,
      String title,
      ExportType exportType,
      HttpServletResponse response,
      MyDynamicReportProperties dynamicReportProperties) {
    super(inputStream, title, null, exportType, response);
    this.dynamicReportProperties = dynamicReportProperties;
  }

  public MyDynamicReportProperties getDynamicReportProperties() {
    return dynamicReportProperties;
  }
}
