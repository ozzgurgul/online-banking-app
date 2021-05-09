package com.ozgurgulbank.onlinebankingapplication.request.account.colorful;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBalanceToColorfulRequest {

    private String accountNo;
    private double amount;

}
