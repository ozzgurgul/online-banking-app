package com.ozgurgulbank.onlinebankingapplication.request.card.credit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayDebtFromDepositRequest {

    private double amount;

    private String accountNo;
    private String cardNo;
}
