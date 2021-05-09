package com.ozgurgulbank.onlinebankingapplication.request.account.deposit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferToDepositRequest {

    private String senderIban;
    private String receiverIban;
    private double amount;

}
