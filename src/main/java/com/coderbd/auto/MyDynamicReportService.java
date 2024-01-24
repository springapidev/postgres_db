package com.coderbd.auto;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

import static com.coderbd.report.domain.report.ExportType.CSV;
import static com.coderbd.report.domain.report.ExportType.EXCEL;
import static com.coderbd.report.util.Constant.EXPORT_TYPE_PARAMETER_IS_MISSING;


@Service
public class MyDynamicReportService {

  public void export(MyDynamicReport dynamicReport) throws JRException, IOException {
    if (dynamicReport.getExportType() == null)
      throw new RuntimeException(EXPORT_TYPE_PARAMETER_IS_MISSING);

    var jasperDynamicReport = dynamicReport.getDynamicReportProperties();
    var jasperReportDesign = JRXmlLoader.load(dynamicReport.getInputStream());

    var reportBuilder =
        new MyDynamicReportBuilder(jasperReportDesign, jasperDynamicReport.getColumnHeaders().size());
    reportBuilder.addDynamicColumns(jasperDynamicReport.getIndexesOfColumnTypeNumber());
    var dataSource =
        new MyDynamicColumnDataSource(
            jasperDynamicReport.getColumnHeaders(),
            jasperDynamicReport.getRows(),
            jasperDynamicReport.getSummary());

    HashMap<String, Object> params = new HashMap();
    params.put("title", dynamicReport.getTitle());
    // This param ignore pagination for excel & csv to prevent duplicate ColumnHeader
    if (dynamicReport.getExportType() == EXCEL
        || dynamicReport.getExportType() == CSV)
      params.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);

    var jasperReport = JasperCompileManager.compileReport(jasperReportDesign);
    var jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
    dynamicReport.exportViaJasperReport(jasperPrint);
  }
}
