package com.ozgurgulbank.onlinebankingapplication.controller;

import com.ozgurgulbank.onlinebankingapplication.request.card.debit.CreateDebitCardRequest;
import com.ozgurgulbank.onlinebankingapplication.service.IDebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/debit-card")
public class DebitController {


    @Autowired
    private IDebitCardService debitService;

    @PostMapping
    public ResponseEntity<Object> createDebit(@RequestBody CreateDebitCardRequest request){

        return debitService.addDebitToDeposit(request);
    }


}
