package com.ozgurgulbank.onlinebankingapplication.service;

import com.ozgurgulbank.onlinebankingapplication.request.card.debit.CreateDebitCardRequest;
import org.springframework.http.ResponseEntity;

public interface IDebitCardService {

    ResponseEntity<Object> addDebitToDeposit(CreateDebitCardRequest request);

}
