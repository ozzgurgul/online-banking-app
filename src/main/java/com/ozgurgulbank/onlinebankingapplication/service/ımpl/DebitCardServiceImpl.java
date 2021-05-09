package com.ozgurgulbank.onlinebankingapplication.service.ımpl;

import com.ozgurgulbank.onlinebankingapplication.entity.card.DebitCard;
import com.ozgurgulbank.onlinebankingapplication.entity.account.DepositAccount;
import com.ozgurgulbank.onlinebankingapplication.exception.ResourceNotFoundException;
import com.ozgurgulbank.onlinebankingapplication.number.CreateNumber;
import com.ozgurgulbank.onlinebankingapplication.repository.DebitRepository;
import com.ozgurgulbank.onlinebankingapplication.repository.DepositRepository;
import com.ozgurgulbank.onlinebankingapplication.request.card.debit.CreateDebitCardRequest;
import com.ozgurgulbank.onlinebankingapplication.service.IDebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DebitCardServiceImpl implements IDebitCardService {

    @Autowired
    private DebitRepository debitRepository;

    @Autowired
    private DepositRepository depositRepository;

    private final CreateNumber createNumber = new CreateNumber();

    @Override
    public ResponseEntity<Object> addDebitToDeposit(CreateDebitCardRequest request) {

        DepositAccount depositAccount = depositRepository.findDepositAccountByAccountNo(request.getAccountNo());
        if(depositAccount==null){

            throw new ResourceNotFoundException("Hesap","hesap numarası",request.getAccountNo());

        }
        DebitCard debitCard = new DebitCard();
        debitCard.setCardNo(createNumber.createCardNo());
        debitCard.setCcv(createNumber.createSecurityNo());
        debitCard.setBalance(depositAccount.getBalance());
        depositAccount.setDebitCard(debitCard);

        debitRepository.save(debitCard);
        depositRepository.save(depositAccount);

        return ResponseEntity.status(HttpStatus.CREATED).body("Banka kartı tanımlandı");

    }

}
