package com.coderbd.report.controller.transaction;

import com.coderbd.report.model.transaction.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(path = "transactions")
public interface TransactionApi {

  @GetMapping
  ResponseEntity<List<Transaction>> getTransactionList();
}
