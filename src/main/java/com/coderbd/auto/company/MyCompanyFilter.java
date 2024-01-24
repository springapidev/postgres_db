package com.coderbd.auto.company;


import com.coderbd.auto.MyDynamicReportProperties;
import com.coderbd.report.domain.report.ExportType;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.coderbd.report.util.Constant.EXTENSION_JRXML;
import static com.coderbd.report.util.Constant.FOLDER_PATH_REPORT_DYNAMIC;

public class MyCompanyFilter {
  ExportType exportType;

  public MyCompanyFilter(ExportType exportType) {
    this.exportType = exportType;
  }

  public InputStream getInputStream() {
    var name = FOLDER_PATH_REPORT_DYNAMIC + this.exportType.toString().toLowerCase() + EXTENSION_JRXML;
    return getClass().getResourceAsStream(name);
  }

  public String getReportTitle() {
    return "Company Summary Report";
  }

  public MyDynamicReportProperties generateDynamicColumnAndRows(List<MyCompany> list) {
    var totalVolume = Double.valueOf(0.00);
    var totalAmount = Double.valueOf(0.00);
    var grandTotalAmount = Double.valueOf(0.00);
    DecimalFormat df = new DecimalFormat("#,###.###");

    List<String> columnHeaders = new ArrayList<>();
    List<Integer> indexesOfColumnTypeNumber = new ArrayList<>();
    List<List<String>> rows = new ArrayList<>();
    List<String> summary = new ArrayList<>();

    // dynamic

    // static
    columnHeaders.add("Id");
    columnHeaders.add("Course Type");
    columnHeaders.add("Description");
    columnHeaders.add("Email");
    columnHeaders.add("Mobile No");
    columnHeaders.add("Price\n(TK)");

    for (var s : list) {
      List<String> row = new ArrayList<>();
      row.add(String.valueOf(s.getId()));
      row.add(s.getCourseType());
      row.add(s.getDescription());
      row.add(s.getEmail());
      row.add(s.getMobileNo());
      row.add(String.valueOf(s.getPrice()));

      rows.add(row);

      // sum
      totalVolume += 100;
      totalAmount += 200;
      grandTotalAmount += 5000;
    }


    // summary
    summary.add("Total");
    summary.add(df.format(totalVolume).toString());
    summary.add(df.format(totalAmount).toString());
   // summary.add(null);// we can keep blank
    summary.add(df.format(grandTotalAmount).toString());

    // number field index list
    indexesOfColumnTypeNumber.add(columnHeaders.size() - 4); // "Total"
    indexesOfColumnTypeNumber.add(columnHeaders.size() - 3); // totalVolume
    indexesOfColumnTypeNumber.add(columnHeaders.size() - 2); // totalAmount
    indexesOfColumnTypeNumber.add(columnHeaders.size() - 1); // totalAmount

    return new MyDynamicReportProperties(columnHeaders, indexesOfColumnTypeNumber, rows, summary);
  }

}