package com.ozgurgulbank.onlinebankingapplication.repository;

import com.ozgurgulbank.onlinebankingapplication.entity.card.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {

    CreditCard findCreditCardByCardNo(String cardNo);

}
