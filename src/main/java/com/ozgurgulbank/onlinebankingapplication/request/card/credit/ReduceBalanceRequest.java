package com.ozgurgulbank.onlinebankingapplication.request.card.credit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReduceBalanceRequest {

    private double amount;
    private String cardNo;
}
