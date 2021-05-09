package com.ozgurgulbank.onlinebankingapplication.request.account.deposit;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.SecondaryTable;

@Getter
@Setter
public class ReduceDepositBalanceRequest {

    private String accountNo;

    private double amount;

}
