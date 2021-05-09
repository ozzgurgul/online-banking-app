package com.ozgurgulbank.onlinebankingapplication.request.card.credit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayDebtFromDebitRequest {

    private double amount;

    private String cardNo;
    private String debitCardNo;


}
