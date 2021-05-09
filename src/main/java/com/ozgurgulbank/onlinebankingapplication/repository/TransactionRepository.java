package com.ozgurgulbank.onlinebankingapplication.repository;

import com.ozgurgulbank.onlinebankingapplication.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findByCreditCardNo(String creditCardNo);

}
