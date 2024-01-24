package com.coderbd.auto;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;

import java.util.Iterator;
import java.util.List;

/** Implementation of a Jasper Reports data source that can contain a dynamic number of columns */
@Slf4j
public class MyDynamicColumnDataSource extends JRAbstractBeanDataSource {
  private final List<String> columnHeaders;
  private final List<List<String>> rows;
  private final List<String> summary;
  private Iterator<List<String>> iterator;
  private List<String> currentRow;

  public MyDynamicColumnDataSource(
      List<String> columnHeaders, List<List<String>> rows, List<String> summary) {
    super(true);
    this.rows = rows;
    this.columnHeaders = columnHeaders;
    this.summary = summary;
    if (this.rows != null) {
      this.iterator = this.rows.iterator();
    }
  }

  @Override
  public boolean next() {
    boolean hasNext = false;
    if (iterator != null) {
      hasNext = iterator.hasNext();
      if (hasNext) {
        this.currentRow = iterator.next();
      }
    }
    return hasNext;
  }

  @Override
  public Object getFieldValue(JRField field) {
    // The name of the field in dynamic columns that were created by DynamicReportBuilder is also
    // the
    // index into the list of columns.
    // For example, if the field is named 'col1', this is the second (because it's zero-based)
    // column in the currentRow.
    String fieldName = field.getName();
    if (fieldName.startsWith(MyDynamicReportBuilder.COL_EXPR_PREFIX)) {
      String indexValue = fieldName.substring(MyDynamicReportBuilder.COL_EXPR_PREFIX.length());
      return currentRow.get(Integer.parseInt(indexValue));
    } else if (fieldName.startsWith(MyDynamicReportBuilder.COL_HEADER_EXPR_PREFIX)) {
      int indexValue =
          Integer.parseInt(
              fieldName.substring(MyDynamicReportBuilder.COL_HEADER_EXPR_PREFIX.length()));
      return columnHeaders.get(indexValue);
    } else if (fieldName.startsWith(MyDynamicReportBuilder.SUMMARY_PREFIX)) {
      int indexValue =
          Integer.parseInt(fieldName.substring(MyDynamicReportBuilder.SUMMARY_PREFIX.length()));
      return summary.get(indexValue);
    } else {
      log.error("MyDynamicColumnDataSource::getFieldValue- The field name {} in the Jasper Report is not valid "+fieldName);
      throw new RuntimeException(
          "The field name '" + fieldName + "' in the Jasper Report is not valid");

    }
  }

  @Override
  public void moveFirst() {
    if (rows != null) {
      iterator = rows.iterator();
    }
  }
}
