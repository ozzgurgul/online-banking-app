package com.ozgurgulbank.onlinebankingapplication.controller;

import com.ozgurgulbank.onlinebankingapplication.request.account.deposit.AddBalanceToDepositRequest;
import com.ozgurgulbank.onlinebankingapplication.request.account.deposit.CreateDepositAccountRequest;
import com.ozgurgulbank.onlinebankingapplication.request.account.deposit.ReduceDepositBalanceRequest;
import com.ozgurgulbank.onlinebankingapplication.request.account.deposit.TransferToDepositRequest;
import com.ozgurgulbank.onlinebankingapplication.service.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/deposit")
public class DepositAccountController {

    @Autowired
    private IDepositService depositService;


    @PostMapping
    public ResponseEntity<Object> createDepositAccount(@RequestBody CreateDepositAccountRequest request){

        return depositService.createDepositAccount(request);

    }

    @PostMapping("/add-money")
    public ResponseEntity<Object> addBalance(@RequestBody AddBalanceToDepositRequest request){

        return depositService.addBalanceToDeposit(request);
    }

    @PostMapping("/reduce-balance")
    public ResponseEntity<Object> redcueBalance(@RequestBody ReduceDepositBalanceRequest request){

        return depositService.reduceBalance(request);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Object> moneyTransfer(@RequestBody TransferToDepositRequest request){

        return depositService.transferToDeposit(request);
    }

}
