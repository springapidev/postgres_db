package com.coderbd.report.controller.transaction;

import com.coderbd.report.model.transaction.Transaction;
import com.coderbd.report.service.transaction.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController implements TransactionApi {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @Override
  public ResponseEntity<List<Transaction>> getTransactionList() {
    return ResponseEntity.ok(transactionService.getTransactionList());
  }
}
