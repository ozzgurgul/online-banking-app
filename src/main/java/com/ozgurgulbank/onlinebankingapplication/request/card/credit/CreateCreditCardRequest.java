package com.ozgurgulbank.onlinebankingapplication.request.card.credit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCreditCardRequest {

    private double limit;

    private long customerId;
}
