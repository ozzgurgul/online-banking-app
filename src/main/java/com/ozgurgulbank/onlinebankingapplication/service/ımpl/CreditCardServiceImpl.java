package com.ozgurgulbank.onlinebankingapplication.service.ımpl;

import com.ozgurgulbank.onlinebankingapplication.calculator.AgeCalculator;
import com.ozgurgulbank.onlinebankingapplication.entity.card.CreditCard;
import com.ozgurgulbank.onlinebankingapplication.entity.card.DebitCard;
import com.ozgurgulbank.onlinebankingapplication.entity.Transaction;
import com.ozgurgulbank.onlinebankingapplication.entity.account.DepositAccount;
import com.ozgurgulbank.onlinebankingapplication.entity.customer.Customer;
import com.ozgurgulbank.onlinebankingapplication.exception.ResourceNotFoundException;
import com.ozgurgulbank.onlinebankingapplication.number.CreateNumber;
import com.ozgurgulbank.onlinebankingapplication.repository.*;
import com.ozgurgulbank.onlinebankingapplication.request.card.credit.*;
import com.ozgurgulbank.onlinebankingapplication.service.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CreditCardServiceImpl implements ICreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DebitRepository debitRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private final CreateNumber createNumber = new CreateNumber();

    private final AgeCalculator ageCalculator = new AgeCalculator();


    @Override
    public ResponseEntity<Object> createCreditCart(CreateCreditCardRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId());

        if(customer==null){
            throw new ResourceNotFoundException("Müşteri","id",request.getCustomerId());
        }

        CreditCard creditCard = new CreditCard();
        creditCard.setCardNo(createNumber.createCardNo());
        creditCard.setCcv(createNumber.createSecurityNo());
        creditCard.setTotalLimit(request.getLimit());
        creditCard.setCurrentLimit(request.getLimit());
        creditCard.setCreatedAt(LocalDate.now());
        creditCard.setCustomer(customer);
        customer.setCreditCard(creditCard);


      int age  = ageCalculator.calculateAge(customer.getBirthDate(),creditCard.getCreatedAt());

      if(age<18 && creditCard.getTotalLimit()>500){

          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("18 yaşında küçükler için üst limit 500 TL");
      }
        creditCardRepository.save(creditCard);

        return ResponseEntity.status(HttpStatus.CREATED).body("Kredi kartı tanımlandı");

    }

    @Override
    public ResponseEntity<Object> reduceBalance(ReduceBalanceRequest request) {

        CreditCard creditCard = creditCardRepository.findCreditCardByCardNo(request.getCardNo());

        if(creditCard==null){
            throw new ResourceNotFoundException("Kredi kartı","kart numarası",request.getCardNo());
        }
        if(creditCard.getCurrentLimit()<request.getAmount()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Yetersiz bakiye");

        }

        creditCard.setCurrentLimit(creditCard.getCurrentLimit()-request.getAmount());

        creditCardRepository.save(creditCard);

        Transaction transaction = new Transaction(0L,request.getCardNo(),"Harcama",request.getAmount(),new Date(System.currentTimeMillis()));
        transactionRepository.save(transaction);

        return ResponseEntity.status(HttpStatus.OK).body("Tutar düşüldü");
    }

    public ResponseEntity<Object> getDebt(String cardNo) {

        CreditCard creditCard = creditCardRepository.findCreditCardByCardNo(cardNo);
        if(creditCard==null){
            throw new ResourceNotFoundException("Kredi kartı","kart numarası",cardNo);
        }

        double debt = creditCard.getTotalLimit()-creditCard.getCurrentLimit();

        return ResponseEntity.status(HttpStatus.OK).body("Güncel Borç : " + debt +" TL");
    }

    public List<Transaction> getStates(String cardNo) {

        return transactionRepository.findByCreditCardNo(cardNo);

    }

    @Override
    public ResponseEntity<Object> payDebtFromDebit(PayDebtFromDebitRequest request) {

        DebitCard debitCard = debitRepository.findDebitCardByCardNo(request.getCardNo());

        if(debitCard==null){
            throw new ResourceNotFoundException("Banka kartı","kart numarası",request.getDebitCardNo());
        }

        CreditCard creditCard = creditCardRepository.findCreditCardByCardNo(request.getCardNo());

        if(creditCard==null){
            throw new ResourceNotFoundException("Kredi kartı","kart numarası",request.getCardNo());
        }

        if(debitCard.getBalance()<request.getAmount()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Yetersiz bakiye");
        }

        creditCard.setCurrentLimit(creditCard.getCurrentLimit()+request.getAmount());
        creditCardRepository.save(creditCard);

        Transaction transaction = new Transaction(0L,request.getCardNo(),"Borç Ödeme",request.getAmount(),new Date(System.currentTimeMillis()));
        transactionRepository.save(transaction);

        return ResponseEntity.status(HttpStatus.OK).body("Borç ödendi");
    }

    @Override
    public ResponseEntity<Object> payDebtFromDeposit(PayDebtFromDepositRequest request) {

        DepositAccount depositAccount = depositRepository.findDepositAccountByAccountNo(request.getAccountNo());
        if(depositAccount==null){

            throw new ResourceNotFoundException("Banka hesabı","hesap numarası",request.getAccountNo());
        }

        CreditCard creditCard = creditCardRepository.findCreditCardByCardNo(request.getCardNo());
        if(creditCard==null){
            throw new ResourceNotFoundException("Kredi kartı","kart numarası",request.getCardNo());
        }

        creditCard.setCurrentLimit(creditCard.getCurrentLimit()+request.getAmount());
        creditCardRepository.save(creditCard);

        Transaction transaction = new Transaction(0L,request.getCardNo(),"Borç Ödeme",request.getAmount(),new Date(System.currentTimeMillis()));
        transactionRepository.save(transaction);

        return ResponseEntity.status(HttpStatus.OK).body("Borç Ödendi");
    }
}
