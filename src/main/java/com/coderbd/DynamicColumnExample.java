package com.coderbd;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicColumnExample {
//
//    public static void main(String[] args) {
//        try {
//            // Design the report dynamically
//            JasperDesign jasperDesign = createDynamicReportDesign();
//
//            // Compile the JasperReport
//            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//
//            // Generate sample data
//            List<Map<String, Object>> data = generateSampleData();
//
//            // Fill the report with data
//            JRDataSource dataSource = new JRBeanCollectionDataSource(data);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
//
//            // Export the report to PDF
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\git\\postgres_db\\src\\main\\resources\\report\\dynamic\\DynamicColumnReport.pdf");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static JasperDesign createDynamicReportDesign() throws JRException {
//        JasperDesign jasperDesign = JRXmlLoader.load("D:\\git\\postgres_db\\src\\main\\resources\\report\\dynamic\\excel.jrxml");
//
//        // Customize the first row with 3 columns
//        customizeRow(jasperDesign, 0, 3);
//
//        // Customize the second row with 15 columns
//        customizeRow(jasperDesign, 1, 15);
//
//        // Customize the third row with 16 columns
//        customizeRow(jasperDesign, 2, 16);
//
//        return jasperDesign;
//    }
//
//    private static void customizeRow(JasperDesign jasperDesign, int rowIndex, int numColumns) {
//        JRDesignBand detailBand = (JRDesignBand) jasperDesign.getDetailSection().getBands()[0];
//
//        // Ensure that the data list is not empty before proceeding
//        List<Map<String, Object>> data = generateSampleData();
//        if (data.isEmpty()) {
//            System.out.println("Data source is empty. Unable to customize row.");
//            return;
//        }
//
//        // Assuming rowIndex is a valid index in the data list
//        Map<String, Object> rowData = data.get(rowIndex);
//
//        // Create text fields dynamically based on the number of columns
//        for (int i = 0; i < numColumns; i++) {
//            JRDesignTextField textField = new JRDesignTextField();
//            textField.setX(i * 50); // Adjust the X position based on your layout
//            textField.setY(rowIndex * 20); // Adjust the Y position based on your layout
//            textField.setWidth(50); // Adjust the width based on your layout
//            textField.setHeight(20); // Adjust the height based on your layout
//
//            // Check if rowData contains the expected column key
//            String columnKey = "Column" + (i + 1);
//            if (rowData.containsKey(columnKey)) {
//                textField.setExpression(new JRDesignExpression("$F{" + columnKey + "}"));
//            } else {
//                System.out.println("Column key " + columnKey + " not found in data.");
//                // Handle the situation where the column key is not found (adjust as needed)
//            }
//
//            detailBand.addElement(textField);
//        }
//    }
//
//
//    private static List<Map<String, Object>> generateSampleData() {
//        List<Map<String, Object>> data = new ArrayList<>();
//
//        // Add sample data for the third row with 16 columns
//        Map<String, Object> row = new HashMap<>();
//        for (int i = 1; i <= 16; i++) {
//            row.put("Column" + i, "Data" + i);
//        }
//        data.add(row);
//
//        return data;
//    }
}
