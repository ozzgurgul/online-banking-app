package com.ozgurgulbank.onlinebankingapplication.controller;

import com.ozgurgulbank.onlinebankingapplication.entity.Transaction;
import com.ozgurgulbank.onlinebankingapplication.request.card.credit.*;
import com.ozgurgulbank.onlinebankingapplication.service.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/credit-card")
public class CreditCardController {

    @Autowired
    private ICreditCardService creditCardService;


    @PostMapping
    public ResponseEntity<Object> createCreditCard(@RequestBody CreateCreditCardRequest request){

        return creditCardService.createCreditCart(request);
    }

    @PostMapping("/reduce")
    public ResponseEntity<Object> reduceBalance(@RequestBody ReduceBalanceRequest request){

        return creditCardService.reduceBalance(request);
    }

    @PostMapping("/pay-debt-debit")
    public ResponseEntity<Object> payDebtFromDebit(@RequestBody PayDebtFromDebitRequest request){

        return creditCardService.payDebtFromDebit(request);
    }

    @PostMapping("/pay-debt-deposit")
    public ResponseEntity<Object> payDebtFromDeposit(@RequestBody PayDebtFromDepositRequest request){

        return creditCardService.payDebtFromDeposit(request);
    }

    @GetMapping("/debt/{cardNo}")
    public ResponseEntity<Object> getDebt(@PathVariable String cardNo  ){

        return creditCardService.getDebt(cardNo);
    }

    @GetMapping("/get-transaction/{cardNo}")
    public List<Transaction> listTransaction(@PathVariable String cardNo){

        return creditCardService.getStates(cardNo);
    }


}
