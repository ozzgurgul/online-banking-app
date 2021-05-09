package com.ozgurgulbank.onlinebankingapplication.controller;

import com.ozgurgulbank.onlinebankingapplication.request.account.colorful.AddBalanceToColorfulRequest;
import com.ozgurgulbank.onlinebankingapplication.request.account.colorful.CreateColorfulAccountRequest;
import com.ozgurgulbank.onlinebankingapplication.service.IColorfulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/colorful")
public class ColorfulAccountController {


    @Autowired
    private IColorfulService colorfulService;

    @PostMapping("/new-account")
    public ResponseEntity<Object> createColorfulAccount(@RequestBody CreateColorfulAccountRequest request){

    return colorfulService.createColorfulAccount(request);

    }

    @PostMapping("/add-balance")
    public ResponseEntity<Object> addBalance(@RequestBody AddBalanceToColorfulRequest request){

        return colorfulService.addBalanceToColorful(request);
    }


}
