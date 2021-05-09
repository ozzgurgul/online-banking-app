package com.ozgurgulbank.onlinebankingapplication.request.account.deposit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBalanceToDepositRequest {

    private String accountNo;

    private double amount;

}
