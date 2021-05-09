package com.ozgurgulbank.onlinebankingapplication.service;

import com.ozgurgulbank.onlinebankingapplication.entity.Transaction;
import com.ozgurgulbank.onlinebankingapplication.request.card.credit.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICreditCardService {

    ResponseEntity<Object> createCreditCart(CreateCreditCardRequest request);
    ResponseEntity<Object> getDebt(String cardNo);
    ResponseEntity<Object> reduceBalance(ReduceBalanceRequest request);
    List<Transaction> getStates(String cardNo);
    ResponseEntity<Object> payDebtFromDebit(PayDebtFromDebitRequest request);
    ResponseEntity<Object> payDebtFromDeposit(PayDebtFromDepositRequest request);


}
