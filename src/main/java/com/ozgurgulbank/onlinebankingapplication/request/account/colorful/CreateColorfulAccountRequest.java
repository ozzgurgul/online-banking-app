package com.ozgurgulbank.onlinebankingapplication.request.account.colorful;

import com.ozgurgulbank.onlinebankingapplication.entity.account.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateColorfulAccountRequest {


    private Currency currency;
    private int expiryDay;

    private long customerId;

}
