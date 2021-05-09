package com.ozgurgulbank.onlinebankingapplication.service.ımpl;

import com.ozgurgulbank.onlinebankingapplication.calculator.InterestCalculator;
import com.ozgurgulbank.onlinebankingapplication.entity.account.ColorfulAccount;
import com.ozgurgulbank.onlinebankingapplication.entity.customer.Customer;
import com.ozgurgulbank.onlinebankingapplication.exception.ResourceNotFoundException;
import com.ozgurgulbank.onlinebankingapplication.number.CreateNumber;
import com.ozgurgulbank.onlinebankingapplication.repository.ColorfulRepository;
import com.ozgurgulbank.onlinebankingapplication.repository.CustomerRepository;
import com.ozgurgulbank.onlinebankingapplication.request.account.colorful.AddBalanceToColorfulRequest;
import com.ozgurgulbank.onlinebankingapplication.request.account.colorful.CreateColorfulAccountRequest;
import com.ozgurgulbank.onlinebankingapplication.service.IColorfulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class ColorfulServiceImpl implements IColorfulService {


    @Autowired
    private ColorfulRepository colorfulRepository;

    @Autowired
    private CustomerRepository customerRepository;


    private final CreateNumber createNumber = new CreateNumber();


    @Override
    public ResponseEntity<Object> createColorfulAccount(CreateColorfulAccountRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId());
        if(customer==null){

            throw  new ResourceNotFoundException("Müşteri","id",request.getCustomerId());

        }

        ColorfulAccount colorfulAccount = new ColorfulAccount();

        colorfulAccount.setCurrency(request.getCurrency());
        colorfulAccount.setExpiryDay(request.getExpiryDay());
        if(colorfulAccount.getExpiryDay()<0 || colorfulAccount.getExpiryDay()>365){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vade günü 0 ile 365 arasında olmalı");
        }
        colorfulAccount.setAccountNo(createNumber.createAccountNumber());
        colorfulAccount.setIbanNo(createNumber.createIban());
        colorfulAccount.setBalance(0);
        customer.setColorfulAccount(Collections.singletonList(colorfulAccount));
        colorfulAccount.setCustomer(customer);

        colorfulRepository.save(colorfulAccount);

        return ResponseEntity.status(HttpStatus.CREATED).body("Renkli hesap oluşturuldu");

    }

    @Override
    public ResponseEntity<Object> addBalanceToColorful(AddBalanceToColorfulRequest request) {

        InterestCalculator calculator = new InterestCalculator();

        ColorfulAccount colorfulAccount = colorfulRepository.findColorfulAccountByAccountNo(request.getAccountNo());
       if(colorfulAccount == null){

           throw new ResourceNotFoundException("Hesap","Hesap Numarası",request.getAccountNo());

       }
        String currency = String.valueOf(colorfulAccount.getCurrency());
        int expiryDay = colorfulAccount.getExpiryDay();
        double amount = request.getAmount();
        double totalInterest;

       if(colorfulAccount.getCustomer().getColorfulAccount().size()!=1 ){

           totalInterest = calculator.getInterest(amount,expiryDay,currency);
       }
       else {

           totalInterest = calculator.getWelcomeInterest(amount,expiryDay,currency);
       }

       colorfulAccount.setBalance(colorfulAccount.getBalance()+request.getAmount()+totalInterest);
       colorfulRepository.save(colorfulAccount);

        return ResponseEntity.status(HttpStatus.OK).body("Tutar eklendi");
    }
}
