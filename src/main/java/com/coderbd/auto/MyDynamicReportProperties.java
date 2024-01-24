package com.coderbd.auto;

import java.util.List;

public class MyDynamicReportProperties {
  List<String> columnHeaders;
  List<Integer> indexesOfColumnTypeNumber;
  List<List<String>> rows;
  List<String> summary;

  public MyDynamicReportProperties() {
  }

  public MyDynamicReportProperties(List<String> columnHeaders, List<Integer> indexesOfColumnTypeNumber, List<List<String>> rows, List<String> summary) {
    this.columnHeaders = columnHeaders;
    this.indexesOfColumnTypeNumber = indexesOfColumnTypeNumber;
    this.rows = rows;
    this.summary = summary;
  }

  public List<String> getColumnHeaders() {
    return columnHeaders;
  }

  public List<Integer> getIndexesOfColumnTypeNumber() {
    return indexesOfColumnTypeNumber;
  }

  public List<List<String>> getRows() {
    return rows;
  }

  public List<String> getSummary() {
    return summary;
  }
}
