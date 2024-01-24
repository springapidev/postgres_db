package com.coderbd.auto;


import com.coderbd.report.domain.report.ExportType;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MyTransactionService {

  private final MyDynamicReportService dynamicReportService;

  public MyTransactionService(MyDynamicReportService dynamicReportService) {
    this.dynamicReportService = dynamicReportService;
  }


  public List<MyTransaction> getTransactionList() {

    List<MyTransaction> transactionList = new ArrayList<>();

    MyTransaction transactionOne = new MyTransaction("2020/11/16", "11:18:26", "SITE TEST",
            "UNI001TEST - Basic2", "00003427 - PCP 3427", "UNI004AS - Salandy Aaron", "979548",
            "IDContribuyente01", "E1A1XONM - 000460", "UNI004AS - Salandy Aaron",
            "Card - 7095030162260000027", "Unleaded Plus ", 0.775, 2.25, 6.75);

    MyTransaction transactionTwo = new MyTransaction("2020/11/16", "11:18:26", "SITE TEST",
            "UNI001TEST - Basic2", "00003427 - PCP 3427", "UNI004AS - Salandy Aaron", "979548",
            "IDContribuyente01", "E1A1XONM - 000460", "UNI004AS - Salandy Aaron",
            "Card - 7095030162260000027", "Unleaded Plus ", 1.225, 3.55, 7.75);

    MyTransaction transactionThree = new MyTransaction("2020/11/17", "11:28:26", "SITE TEST",
            "UNI001TEST - Basic2", "00003427 - PCP 3427", "UNI004AS - JamilXT", "979548",
            "IDContribuyente02", "E1A1XONM - 000460", "UNI004AS - JamilXT",
            "Card - 7095030162260000027", "Unleaded Plus ", 1.775, 4.75, 8.75);

    MyTransaction transactionFour = new MyTransaction("2020/11/18", "12:28:26", "SITE TEST",
            "UNI001TEST - Basic3", "00003427 - PCP 3427", "UNI004AS - Faisal", "979548",
            "IDContribuyente03", "E1A1XONM - 000460", "UNI004AS - Faisal",
            "Card - 7095030162260000027", "Unleaded Plus ", 2.225, 5.95, 6.75);

    transactionList.add(transactionOne);
    transactionList.add(transactionTwo);
    transactionList.add(transactionThree);
    transactionList.add(transactionFour);

    return transactionList;
  }

  public void exportTransactionReport(ExportType exportType, HttpServletResponse response) throws JRException, IOException {
    var filter = new MyTransactionFilter(exportType);
    var inputStream = filter.getInputStream();
    var title = filter.getReportTitle();
    var list = getTransactionList();

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
