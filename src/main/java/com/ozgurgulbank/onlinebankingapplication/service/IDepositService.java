package com.ozgurgulbank.onlinebankingapplication.service;

import com.ozgurgulbank.onlinebankingapplication.request.account.deposit.AddBalanceToDepositRequest;
import com.ozgurgulbank.onlinebankingapplication.request.account.deposit.CreateDepositAccountRequest;
import com.ozgurgulbank.onlinebankingapplication.request.account.deposit.ReduceDepositBalanceRequest;
import com.ozgurgulbank.onlinebankingapplication.request.account.deposit.TransferToDepositRequest;
import org.springframework.http.ResponseEntity;

public interface IDepositService {

    ResponseEntity<Object> createDepositAccount(CreateDepositAccountRequest request);
    ResponseEntity<Object> addBalanceToDeposit(AddBalanceToDepositRequest request);
    ResponseEntity<Object> reduceBalance(ReduceDepositBalanceRequest request);
    ResponseEntity<Object> transferToDeposit(TransferToDepositRequest request);

}
