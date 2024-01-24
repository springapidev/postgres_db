package com.coderbd.auto;

import com.coderbd.auto.company.MyCompanyService;
import com.coderbd.report.domain.report.ExportType;
import com.coderbd.service.MyReportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping(value = "/dynamic/")
@Slf4j
@AllArgsConstructor
public class MyReportController  {

  private final MyReportService myReportService;
  private final MyTransactionService myTransactionService;
  private final MyCompanyService myCompanyService;


//http://localhost:8081/dynamic/download?exportType=EXCEL
  @GetMapping(value = "download")
  ResponseEntity<String> exportDynamicReport(@RequestParam(value = "exportType") ExportType exportType,
                                                      HttpServletResponse response) throws IOException, JRException{
   try{
       myTransactionService.exportTransactionReport(exportType, response);
   }catch (Exception e){
     log.error("MyReportController::exportDynamicReport() {} "+e.getMessage());
   }
    return ResponseEntity.ok().build();
  }

  //http://localhost:8081/dynamic/company/download?exportType=EXCEL
    @GetMapping(value = "company/download")
    ResponseEntity<String> exportDynamicCompanyReport(@RequestParam(value = "exportType") ExportType exportType,
                                               HttpServletResponse response) throws IOException, JRException{
        try{
            myCompanyService.exportCompanyReport(exportType, response);
        }catch (Exception e){
            log.error("MyReportController::exportDynamicCompanyReport() {} "+e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
