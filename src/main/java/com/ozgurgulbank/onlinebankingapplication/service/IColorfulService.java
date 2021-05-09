package com.ozgurgulbank.onlinebankingapplication.service;

import com.ozgurgulbank.onlinebankingapplication.request.account.colorful.AddBalanceToColorfulRequest;
import com.ozgurgulbank.onlinebankingapplication.request.account.colorful.CreateColorfulAccountRequest;
import org.springframework.http.ResponseEntity;

public interface IColorfulService {

    ResponseEntity<Object> createColorfulAccount(CreateColorfulAccountRequest request);
    ResponseEntity<Object> addBalanceToColorful(AddBalanceToColorfulRequest request);


}
