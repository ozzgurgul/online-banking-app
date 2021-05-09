package com.ozgurgulbank.onlinebankingapplication.service.ımpl;

import com.ozgurgulbank.onlinebankingapplication.calculator.ProcessingFeeCalculator;
import com.ozgurgulbank.onlinebankingapplication.entity.account.DepositAccount;
import com.ozgurgulbank.onlinebankingapplication.entity.customer.Customer;
import com.ozgurgulbank.onlinebankingapplication.exception.ResourceNotFoundException;
import com.ozgurgulbank.onlinebankingapplication.number.CreateNumber;
import com.ozgurgulbank.onlinebankingapplication.repository.CustomerRepository;
import com.ozgurgulbank.onlinebankingapplication.repository.DebitRepository;
import com.ozgurgulbank.onlinebankingapplication.repository.DepositRepository;
import com.ozgurgulbank.onlinebankingapplication.request.account.deposit.AddBalanceToDepositRequest;
import com.ozgurgulbank.onlinebankingapplication.request.account.deposit.CreateDepositAccountRequest;
import com.ozgurgulbank.onlinebankingapplication.request.account.deposit.ReduceDepositBalanceRequest;
import com.ozgurgulbank.onlinebankingapplication.request.account.deposit.TransferToDepositRequest;
import com.ozgurgulbank.onlinebankingapplication.service.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class DepositServiceImpl implements IDepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DebitRepository debitRepository;

    private final CreateNumber createNumber = new CreateNumber();

    private final ProcessingFeeCalculator processingFeeCalculator = new ProcessingFeeCalculator();


    @Override
    public ResponseEntity<Object> createDepositAccount(CreateDepositAccountRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId());
        if(customer==null){

            throw new ResourceNotFoundException("Müşteri","id",request.getCustomerId());

        }
        if(customer.getDepositAccount()!=null){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Müşteri ikinci bir vadesiz hesap açamaz !");
        }

        DepositAccount depositAccount = new DepositAccount();

        depositAccount.setAccountNo(createNumber.createAccountNumber());
        depositAccount.setIbanNo(createNumber.createIban());
        depositAccount.setBalance(0);
        customer.setDepositAccount(depositAccount);

        depositRepository.save(depositAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body("Hesap oluşturuldu");

    }

    @Override
    public ResponseEntity<Object> addBalanceToDeposit(AddBalanceToDepositRequest request) {

        DepositAccount depositAccount = depositRepository.findDepositAccountByAccountNo(request.getAccountNo());
        if(depositAccount==null){

            throw new ResourceNotFoundException("Hesap","hesap numarası",request.getAccountNo());

        }

        double amount = request.getAmount();
        depositAccount.setBalance(depositAccount.getBalance()+amount);

        if(depositAccount.getDebitCard()!=null){
            depositAccount.getDebitCard().setBalance(depositAccount.getBalance());
        }

        depositRepository.save(depositAccount);

        return ResponseEntity.status(HttpStatus.OK).body("Tutar eklendi");
    }

    @Override
    public ResponseEntity<Object> reduceBalance(ReduceDepositBalanceRequest request) {

        DepositAccount depositAccount = depositRepository.findDepositAccountByAccountNo(request.getAccountNo());
        if(depositAccount==null){
            throw new ResourceNotFoundException("Hesap","hesap numarası",request.getAccountNo());
        }

        double amount = request.getAmount();
        if(amount> depositAccount.getBalance()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Yetersiz bakiye");
        }
        if(amount<0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lütfen geçerli bir tutar giriniz.");
        }

        depositAccount.setBalance(depositAccount.getBalance()-amount);
        depositAccount.setDebitCard(depositAccount.getDebitCard());

        if(depositAccount.getDebitCard()!=null){
            depositAccount.getDebitCard().setBalance(depositAccount.getBalance());
        }
        if(amount<0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lütfen geçerli bir tutar giriniz.");
        }

        depositRepository.save(depositAccount);

        return ResponseEntity.status(HttpStatus.OK).body("Tutar düşüldü");
    }

    @Override
    public ResponseEntity<Object> transferToDeposit(TransferToDepositRequest request) {

        DepositAccount sender = depositRepository.findDepositAccountByAccountNo(request.getSenderIban());
        if(sender==null){
            throw new ResourceNotFoundException("Gönderici","hesap numarası",request.getSenderIban());
        }
        DepositAccount receiver = depositRepository.findDepositAccountByAccountNo(request.getReceiverIban());
        if(receiver==null){
            throw new ResourceNotFoundException("Alıcı","hesap numarası",request.getReceiverIban());
        }

        double amount = request.getAmount();
        double fee= processingFeeCalculator.calculateFeeForAccountToAccount(amount);

        if(amount+fee> sender.getBalance()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Yetersiz Bakiye");
        }
        if(amount<0){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lütfen geçerli bir tutar giriniz");
        }

        sender.setBalance(sender.getBalance()-amount-fee);
        receiver.setBalance(receiver.getBalance()+amount);

        depositRepository.save(sender);
        depositRepository.save(receiver);

        return ResponseEntity.status(HttpStatus.OK).body("Transfer işlemi başarılı");

    }
}
