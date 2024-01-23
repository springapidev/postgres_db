package com.coderbd.service;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface ReportService {
    String exportReport(String ReportType, int page, int size) throws FileNotFoundException, JRException;
}
